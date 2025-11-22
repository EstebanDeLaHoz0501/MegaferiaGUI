/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.book.Audiobook;
import core.models.book.Book;
import core.models.book.DigitalBook;
import core.models.book.PrintedBook;
import core.models.megaferia.Megaferia;
import core.models.megaferia.MfAddBook;
import core.models.megaferia.MfGetAuthor;
import core.models.megaferia.MfGetNarrator;
import core.models.megaferia.MfGetPublisher;
import core.models.person.author.Author;
import core.models.person.author.AuthorAddBook;
import core.models.person.author.AuthorGetPublisherQuantity;
import core.models.person.narrator.Narrator;
import core.models.person.narrator.NarratorAddBook;
import core.models.publisher.Publisher;
import core.models.publisher.PublisherAddBook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class BookController {
    
    private core.models.megaferia.IMegaferiaContext megaferia; 
    public BookController(core.models.megaferia.IMegaferiaContext megaferia) {
        this.megaferia = megaferia;
    }

    private Response validarDatosComunes(String titulo, String isbn, double valor, String editorialNit, ArrayList<Long> autoresIds) {
        if (titulo.isEmpty() || isbn.isEmpty() || editorialNit.isEmpty() || autoresIds.isEmpty()) {
            return new Response(false, "Error: Todos los campos obligatorios deben ser llenados.", Status.BAD_REQUEST);
        }
        
        if (valor <= 0) {
            return new Response(false, "Error: El valor debe ser superior a 0.", Status.BAD_REQUEST);
        }
        
        if (!isbn.matches("\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}")) {
            return new Response(false, "Error: El ISBN no cumple el formato XXX-X-XX-XXXXXX-X.", Status.BAD_REQUEST);
        }
        
        for (Book b : megaferia.getBooks()) {
            if (b.getIsbn().equals(isbn)) {
                return new Response(false, "Error: El ISBN ya existe en el sistema.", Status.BAD_REQUEST);
            }
        }
        
        return new Response(true, "Validación exitosa", Status.OK);
    }

    public Response crearLibroImpreso(String titulo, String isbn, String genero, String formato, String valorStr, String editorialStr, String autoresStr, String paginasStr, String ejemplaresStr) {
        try {
            double valor = Double.parseDouble(valorStr);
            int paginas = Integer.parseInt(paginasStr);
            int ejemplares = Integer.parseInt(ejemplaresStr);
            
            String editorialNit = extraerNit(editorialStr);
            ArrayList<Long> autoresIds = procesarAutores(autoresStr);

            Response validacion = validarDatosComunes(titulo, isbn, valor, editorialNit, autoresIds);
            if (!validacion.isSuccess()) return validacion;

            Publisher publisher = new MfGetPublisher().getPublisher(megaferia, editorialNit); 
            if (publisher == null) return new Response(false, "Error: Editorial no encontrada.", Status.NOT_FOUND);
            
            ArrayList<Author> authors = obtenerListaAutores(autoresIds);
            
            PrintedBook book = new PrintedBook(titulo, authors, isbn, genero, formato, valor, publisher, paginas, ejemplares);
            guardarLibro(book, publisher, authors);
            
            return new Response(true, "Libro impreso creado con éxito.", Status.CREATED);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique que los campos numéricos sean correctos.", Status.BAD_REQUEST);
        } catch (Exception e) {
             return new Response(false, "Error inesperado al crear el libro.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response crearLibroDigital(String titulo, String isbn, String genero, String formato, String valorStr, String editorialStr, String autoresStr, String hipervinculo) {
        try {
            double valor = Double.parseDouble(valorStr);
            String editorialNit = extraerNit(editorialStr);
            ArrayList<Long> autoresIds = procesarAutores(autoresStr);

            Response validacion = validarDatosComunes(titulo, isbn, valor, editorialNit, autoresIds);
            if (!validacion.isSuccess()) return validacion;

            Publisher publisher = new MfGetPublisher().getPublisher(megaferia, editorialNit);
            ArrayList<Author> authors = obtenerListaAutores(autoresIds);

            DigitalBook book;
            if (hipervinculo == null || hipervinculo.trim().isEmpty()) {
                book = new DigitalBook(titulo, authors, isbn, genero, formato, valor, publisher);
            } else {
                book = new DigitalBook(titulo, authors, isbn, genero, formato, valor, publisher, hipervinculo);
            }
            guardarLibro(book, publisher, authors);

            return new Response(true, "Libro digital creado con éxito.", Status.CREATED);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique el valor monetario.", Status.BAD_REQUEST);
        }
    }

    public Response crearAudioLibro(String titulo, String isbn, String genero, String formato, String valorStr, String editorialStr, String autoresStr, String duracionStr, String narradorStr) {
        try {
            double valor = Double.parseDouble(valorStr);
            int duracion = Integer.parseInt(duracionStr);
            String editorialNit = extraerNit(editorialStr);
            ArrayList<Long> autoresIds = procesarAutores(autoresStr);
            
            if(narradorStr == null || narradorStr.equals("Seleccione uno...")) {
                 return new Response(false, "Error: Debe seleccionar un narrador.", Status.BAD_REQUEST);
            }
            long narradorId = Long.parseLong(narradorStr.split(" - ")[0]);

            Response validacion = validarDatosComunes(titulo, isbn, valor, editorialNit, autoresIds);
            if (!validacion.isSuccess()) return validacion;

            Publisher publisher = new MfGetPublisher().getPublisher(megaferia, editorialNit);
            Narrator narrator = new MfGetNarrator().getNarrator(megaferia, narradorId);
            ArrayList<Author> authors = obtenerListaAutores(autoresIds);

            Audiobook book = new Audiobook(titulo, authors, isbn, genero, formato, valor, publisher, duracion, narrator);
            
            guardarLibro(book, publisher, authors);
            new NarratorAddBook().addBook(narrator, book);

            return new Response(true, "Audiolibro creado con éxito.", Status.CREATED);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique duración o valor.", Status.BAD_REQUEST);
        }
    }
    
    public Response listarLibros(String search) { 
        if (this.megaferia.getBooks().isEmpty()) {
            return new Response(false, "No hay libros en la base de datos", Status.NO_CONTENT);
        }
        
        ArrayList<Book> libros = new ArrayList<>(this.megaferia.getBooks());
        ordenarLibrosPorISBN(libros);      
        ArrayList<Object[]> filas = filtrarYFormatearLibros(libros, search);
        
        return new Response(true, "Libros listados correctamente", Status.OK, filas);
    }
    
    public Response buscarPorAutor(String[] authorData) {               
        MfGetAuthor buscadorAutor = new MfGetAuthor();                                            
        try {
            long authorId = Long.parseLong(authorData[0]);
            Author author = buscadorAutor.getAuthor(this.megaferia, authorId);
            
            if (author == null || author.getBooks().isEmpty()) {
                return new Response(false, "Este autor no tiene libros", Status.NO_CONTENT);
            }
            ArrayList<Book> libros = new ArrayList<>(author.getBooks());
            ordenarLibrosPorISBN(libros);   
            ArrayList<Object[]> filas = filtrarYFormatearLibros(libros, "Todos los Libros");
            
            return new Response(true, "Libros del autor encontrados", Status.OK, filas);
            
        } catch (Exception e) {
            return new Response(false, "Error seleccionando autor", Status.BAD_REQUEST);
        }
    }
    
public Response filterByFormat(String bookFormat) { 
        try {
            ArrayList<Book> filteredBooks = filterBooks(bookFormat);
            
            if (filteredBooks.isEmpty()) {
                return new Response(false, "No hay libros con ese formato", Status.NO_CONTENT);
            }
            
            ordenarLibrosPorISBN(filteredBooks); 
            ArrayList<Object[]> filas = filtrarYFormatearLibros(filteredBooks, "Todos los Libros");
            
            return new Response(true, "Libros filtrados exitosamente", Status.OK, filas);
            
        } catch (Exception e) {
            return new Response(false, "Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
public Response getAuthorsWithMaxPublishers() {
        ArrayList<Author> authorsMax = new ArrayList<>();
        int maxPublishers = -1;
        
        try {
            for (Author author : this.megaferia.getAuthors()) { 
                int quantity = AuthorGetPublisherQuantity.getPublisherQuantity(author);
                if (quantity > maxPublishers) {
                    maxPublishers = quantity;
                    authorsMax.clear();
                    authorsMax.add(author);
                } else if (quantity == maxPublishers) {
                    authorsMax.add(author);
                }
            } 
            
            Collections.sort(authorsMax, new Comparator<Author>() {
                @Override
                public int compare(Author a1, Author a2) {
                    return Long.compare(a1.getId(), a2.getId());
                }
            }); 
            
            return new Response(true, "Autores encontrados", Status.OK, authorsMax);

        } catch (Exception e) {
            return new Response(false, "Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    private String extraerNit(String editorialStr) {
        if (editorialStr == null || !editorialStr.contains("(") || !editorialStr.contains(")")) return "";
        return editorialStr.split("\\(")[1].replace(")", "");
    }

    private ArrayList<Long> procesarAutores(String autoresStr) {
        ArrayList<Long> ids = new ArrayList<>();
        if (autoresStr == null || autoresStr.trim().isEmpty()) return ids;
        String[] lines = autoresStr.split("\n");
        for (String line : lines) {
            if (!line.trim().isEmpty() && line.contains(" - ")) {
                try {
                    ids.add(Long.parseLong(line.split(" - ")[0]));
                } catch (NumberFormatException e) {
                    
                }
            }
        }
        return ids;
    }

    private ArrayList<Author> obtenerListaAutores(ArrayList<Long> ids) {
        ArrayList<Author> list = new ArrayList<>();
        MfGetAuthor getter = new MfGetAuthor();
        for (Long id : ids) {
            Author a = getter.getAuthor(megaferia, id);
            if (a != null) list.add(a);
        }
        return list;
    }

    private void guardarLibro(Book book, Publisher publisher, ArrayList<Author> authors) {
        new MfAddBook().addBook(megaferia, book);      
        new PublisherAddBook().publisherAddBook(publisher, book);
        
        AuthorAddBook authorAdder = new AuthorAddBook();
        for (Author a : authors) {
            authorAdder.addBook(a, book);
        }
    }
    
    private ArrayList<Book> filterBooks(String bookFormat) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : megaferia.getBooks()) {
            if (book.getFormat().equals(bookFormat)) {
                result.add(book);
            }
        }

        return result;
    }
   
    private void ordenarLibrosPorISBN(ArrayList<Book> libros) {
        Collections.sort(libros, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getIsbn().compareTo(b2.getIsbn());
            }
        });
    }

    private ArrayList<Object[]> filtrarYFormatearLibros(ArrayList<Book> libros, String search) {
        ArrayList<Object[]> filas = new ArrayList<>();
        for (Book book : libros) {
            boolean match = false;
            
            if (search.equals("Todos los Libros")) match = true;
            else if (search.equals("Libros Impresos") && book instanceof PrintedBook) match = true;
            else if (search.equals("Libros Digitales") && book instanceof DigitalBook) match = true;
            else if (search.equals("Audiolibros") && book instanceof Audiobook) match = true;

            if (match) {
                String authors = "";
                if (!book.getAuthors().isEmpty()) {
                    authors = book.getAuthors().get(0).getFullname();
                    for (int i = 1; i < book.getAuthors().size(); i++) {
                        authors += (", " + book.getAuthors().get(i).getFullname());
                    }
                }

                Object[] row = null;
                if (book instanceof PrintedBook p) {
                    row = new Object[]{p.getTitle(), authors, p.getIsbn(), p.getGenre(), p.getFormat(), p.getValue(), p.getPublisher().getName(), p.getCopies(), p.getPages(), "-", "-", "-"};
                } else if (book instanceof DigitalBook d) {
                    row = new Object[]{d.getTitle(), authors, d.getIsbn(), d.getGenre(), d.getFormat(), d.getValue(), d.getPublisher().getName(), "-", "-", d.hasHyperlink() ? d.getHyperlink() : "No", "-", "-"};
                } else if (book instanceof Audiobook a) {
                    row = new Object[]{a.getTitle(), authors, a.getIsbn(), a.getGenre(), a.getFormat(), a.getValue(), a.getPublisher().getName(), "-", "-", "-", a.getNarrador().getFullname(), a.getDuration()};
                }
                
                if (row != null) filas.add(row);
            }
        }
        return filas;
    }   
}   

