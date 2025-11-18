/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import model.Megaferia.Megaferia;
import model.Megaferia.MfAddAuthor;
import model.Megaferia.MfAddManager;
import model.Megaferia.MfAddNarrator;
import model.Person.Author;
import model.Person.Manager;
import model.Person.Narrator;
import model.Person.Person;

/**
 *
 * @author FERNANDO
 */
public class PersonaController {
    
    private Megaferia megaferia;

    public PersonaController() {
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

        return new Response(true, "Autor " + nombre + " creado con éxito.");
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

        return new Response(true, "Gerente " + nombre + " creado con éxito.");
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

        return new Response(true, "Narrador " + nombre + " creado con éxito.");
    }


    private Response validarDatosPersona(String idStr, String nombre, String apellido) {
        
        if (idStr.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            return new Response(false, "Error: Todos los campos (ID, Nombre, Apellido) son obligatorios.");
        }

        long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: El ID debe ser un número.");
        }

        if (id < 0 || idStr.length() > 15) {
            return new Response(false, "Error: El ID debe ser positivo y de máximo 15 dígitos.");
        }

        for (Person p : megaferia.getAuthors()) {
            if (p.getId() == id) {
                return new Response(false, "Error: El ID ya existe (pertenece a un Autor).");
            }
        }
        for (Person p : megaferia.getManagers()) {
            if (p.getId() == id) {
                return new Response(false, "Error: El ID ya existe (pertenece a un Gerente).");
            }
        }
        for (Person p : megaferia.getNarrators()) {
            if (p.getId() == id) {
                return new Response(false, "Error: El ID ya existe (pertenece a un Narrador).");
            }
        }
        
        return new Response(true, "Validación exitosa.");
    }
    
}
