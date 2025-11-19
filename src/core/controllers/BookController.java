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
import core.models.person.narrator.Narrator;
import core.models.person.narrator.NarratorAddBook;
import core.models.publisher.Publisher;
import core.models.publisher.PublisherAddBook;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class BookController {
    
    private Megaferia megaferia;

    public BookController() {
        this.megaferia = Megaferia.getInstance();
    }

    // --- 1. Método de Validación (Reutilizable) ---
    private Response validarDatosComunes(String titulo, String isbn, double valor, String editorialNit, ArrayList<Long> autoresIds) {
        if (titulo.isEmpty() || isbn.isEmpty() || editorialNit.isEmpty() || autoresIds.isEmpty()) {
            return new Response(false, "Error: Todos los campos obligatorios deben ser llenados.", Status.BAD_REQUEST);
        }
        
        if (valor <= 0) {
            return new Response(false, "Error: El valor debe ser superior a 0.", Status.BAD_REQUEST);
        }
        
        // Validación estricta del formato ISBN (XXX-X-XX-XXXXXX-X)
        if (!isbn.matches("\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}")) {
            return new Response(false, "Error: El ISBN no cumple el formato XXX-X-XX-XXXXXX-X.", Status.BAD_REQUEST);
        }
        
        // Validación de ISBN único
        for (Book b : megaferia.getBooks()) {
            if (b.getIsbn().equals(isbn)) {
                return new Response(false, "Error: El ISBN ya existe en el sistema.", Status.BAD_REQUEST);
            }
        }
        
        return new Response(true, "Validación exitosa", Status.OK);
    }

    // --- 2. Crear Libro Impreso ---
    public Response crearLibroImpreso(String titulo, String isbn, String genero, String formato, String valorStr, String editorialStr, String autoresStr, String paginasStr, String ejemplaresStr) {
        try {
            double valor = Double.parseDouble(valorStr);
            int paginas = Integer.parseInt(paginasStr);
            int ejemplares = Integer.parseInt(ejemplaresStr);
            
            // Extraer el NIT del String "Nombre (NIT)" que viene de la vista
            String editorialNit = extraerNit(editorialStr);
            ArrayList<Long> autoresIds = procesarAutores(autoresStr);

            Response validacion = validarDatosComunes(titulo, isbn, valor, editorialNit, autoresIds);
            if (!validacion.isSuccess()) return validacion;

            // Buscar objetos usando las clases Helper (SRP)
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

    // --- 3. Crear Libro Digital ---
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

    // --- 4. Crear Audio Libro ---
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
            // Vinculación extra para audiolibros
            new NarratorAddBook().addBook(narrator, book);

            return new Response(true, "Audiolibro creado con éxito.", Status.CREATED);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: Verifique duración o valor.", Status.BAD_REQUEST);
        }
    }

    // --- MÉTODOS PRIVADOS DE AYUDA ---

    // Parsea el String "Nombre (NIT)" para sacar solo el NIT
    private String extraerNit(String editorialStr) {
        if (editorialStr == null || !editorialStr.contains("(") || !editorialStr.contains(")")) return "";
        return editorialStr.split("\\(")[1].replace(")", "");
    }

    // Parsea el String del TextArea de autores para sacar los IDs
    private ArrayList<Long> procesarAutores(String autoresStr) {
        ArrayList<Long> ids = new ArrayList<>();
        if (autoresStr == null || autoresStr.trim().isEmpty()) return ids;
        String[] lines = autoresStr.split("\n");
        for (String line : lines) {
            if (!line.trim().isEmpty() && line.contains(" - ")) {
                try {
                    ids.add(Long.parseLong(line.split(" - ")[0]));
                } catch (NumberFormatException e) {
                    // Ignorar líneas mal formadas
                }
            }
        }
        return ids;
    }

    // Convierte lista de IDs en lista de objetos Author
    private ArrayList<Author> obtenerListaAutores(ArrayList<Long> ids) {
        ArrayList<Author> list = new ArrayList<>();
        MfGetAuthor getter = new MfGetAuthor();
        for (Long id : ids) {
            Author a = getter.getAuthor(megaferia, id);
            if (a != null) list.add(a);
        }
        return list;
    }

    // Centraliza el guardado y las relaciones bidireccionales
    private void guardarLibro(Book book, Publisher publisher, ArrayList<Author> authors) {
        // 1. Guardar en Megaferia
        new MfAddBook().addBook(megaferia, book);
        
        // 2. Vincular con Editorial (Usa tu clase PublisherAddBook)
        new PublisherAddBook().publisherAddBook(publisher, book);
        
        // 3. Vincular con Autores (Usa tu clase AuthorAddBook)
        AuthorAddBook authorAdder = new AuthorAddBook();
        for (Author a : authors) {
            authorAdder.addBook(a, book);
        }
    }
    
    
    
}
