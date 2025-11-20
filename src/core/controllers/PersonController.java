/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.megaferia.Megaferia;
import core.models.megaferia.MfAddAuthor;
import core.models.megaferia.MfAddManager;
import core.models.megaferia.MfAddNarrator;
import core.models.person.author.Author;
import core.models.person.Manager;
import core.models.person.narrator.Narrator;
import core.models.person.Person;
import core.models.person.author.AuthorGetBookQuantity;
import core.models.person.narrator.NarratorGetBookQuantity;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FERNANDO
 */
public class PersonController {
    
    private Megaferia megaferia;

    public PersonController() {
        this.megaferia = Megaferia.getInstance();
    }

    // Crear autor
    public Response crearAutor(String idStr, String nombre, String apellido) {
        

        Response validationResponse = validarDatosPersona(idStr, nombre, apellido);
        if (!validationResponse.isSuccess()) {
            return validationResponse; 
        }
        
        long id = Long.parseLong(idStr);

        Author nuevoAutor = new Author(id, nombre, apellido);

        MfAddAuthor mfAdd = new MfAddAuthor();
        mfAdd.addAuthor(megaferia, nuevoAutor);

        return new Response(true, "Autor " + nombre + " creado con éxito.", Status.CREATED);
    }

    // Crear gerente
    public Response crearGerente(String idStr, String nombre, String apellido) {
        
        Response validationResponse = validarDatosPersona(idStr, nombre, apellido);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        
        long id = Long.parseLong(idStr);

        Manager nuevoGerente = new Manager(id, nombre, apellido);

        MfAddManager mfAdd = new MfAddManager();
        mfAdd.addManager(megaferia, nuevoGerente);

        return new Response(true, "Gerente " + nombre + " creado con éxito.", Status.CREATED);
    }

    // Crear narrador
    public Response crearNarrador(String idStr, String nombre, String apellido) {
        
        Response validationResponse = validarDatosPersona(idStr, nombre, apellido);
        if (!validationResponse.isSuccess()) {
            return validationResponse;
        }
        
        long id = Long.parseLong(idStr);

        Narrator nuevoNarrador = new Narrator(id, nombre, apellido);

        MfAddNarrator mfAdd = new MfAddNarrator();
        mfAdd.addNarrator(megaferia, nuevoNarrador);

        return new Response(true, "Narrador " + nombre + " creado con éxito.", Status.CREATED);
    }


    private Response validarDatosPersona(String idStr, String nombre, String apellido) {
        
        if (idStr.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            return new Response(false, "Error: Todos los campos (ID, Nombre, Apellido) son obligatorios.", Status.BAD_REQUEST);
        }

        long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: El ID debe ser un número.", Status.BAD_REQUEST);
        }

        if (id < 0 || idStr.length() > 15) {
            return new Response(false, "Error: El ID debe ser positivo y de máximo 15 dígitos.", Status.BAD_REQUEST);
        }

        for (Person p : megaferia.getAuthors()) {
            if (p.getId() == id) {
                return new Response(false, "Error: El ID ya existe (pertenece a un Autor).", Status.BAD_REQUEST);
            }
        }
        for (Person p : megaferia.getManagers()) {
            if (p.getId() == id) {
                return new Response(false, "Error: El ID ya existe (pertenece a un Gerente).", Status.BAD_REQUEST);
            }
        }
        for (Person p : megaferia.getNarrators()) {
            if (p.getId() == id) {
                return new Response(false, "Error: El ID ya existe (pertenece a un Narrador).", Status.BAD_REQUEST);
            }
        }
        
        return new Response(true, "Validación exitosa.", Status.OK);
    }
    public Response ShowPersonas (DefaultTableModel model){
        AuthorGetBookQuantity agbq = new AuthorGetBookQuantity();
        NarratorGetBookQuantity ngbq = new NarratorGetBookQuantity();
        if(this.megaferia.getManagers().isEmpty() & this.megaferia.getNarrators().isEmpty() & this.megaferia.getAuthors().isEmpty())
            return new Response(false, "No hay personas en la base de datos", Status.NO_CONTENT);
        else{
            model.setRowCount(0);
            for (Author author : megaferia.getAuthors()) {
                model.addRow(new Object[]{author.getId(), author.getFullname(), "Autor", "-", agbq.getBookQuantity(author)});
            }
            for (Manager manager : megaferia.getManagers()) {
                model.addRow(new Object[]{manager.getId(), manager.getFullname(), "Gerente", manager.getPublisher().getName(), 0});
            }
            for (Narrator narrator : megaferia.getNarrators()) {
                model.addRow(new Object[]{narrator.getId(), narrator.getFullname(), "Narrador", "-", ngbq.getBookQuantity(narrator)});
            }
            return new Response(true, "Editoriales válidas", Status.OK);
        }
    }
}
