/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import java.util.ArrayList;
import model.Megaferia.Megaferia;
import model.Megaferia.MfAddPublisher;
import model.Person.Manager;
import model.Publisher.Publisher;

/**
 *
 * @author FERNANDO
 */
public class EditorialController {
    
    private Megaferia megaferia;

    public EditorialController() {
        this.megaferia = Megaferia.getInstance();
    }


    public Response crearEditorial(String nit, String nombre, String direccion, String managerStr) {
        
        if (nit.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
            return new Response(false, "Error: Los campos NIT, Nombre y Dirección son obligatorios.");
        }

        String nitPattern = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{1}";
        if (!nit.matches(nitPattern)) {
            return new Response(false, "Error: El NIT no cumple con el formato XXX.XXX.XXX-X (Ej: 900.123.456-7).");
        }

        ArrayList<Publisher> listaEditoriales = this.megaferia.getPublishers();
        for (Publisher editorialExistente : listaEditoriales) {
            if (editorialExistente.getNit().equals(nit)) {
                return new Response(false, "Error: El NIT de la editorial ya existe.");
            }
        }
        
        if (managerStr == null || managerStr.equals("Seleccione uno...")) {
            return new Response(false, "Error: Debe seleccionar un Gerente válido.");
        }
        
        long managerId;
        Manager gerenteSeleccionado = null;
        try {
            String[] managerData = managerStr.split(" - ");
            managerId = Long.parseLong(managerData[0]);
        } catch (Exception e) {
            return new Response(false, "Error: El formato del Gerente seleccionado es inválido.");
        }
        
        for (Manager manager : this.megaferia.getManagers()) {
            if (manager.getId() == managerId) {
                gerenteSeleccionado = manager;
                break;
            }
        }

        if (gerenteSeleccionado == null) {
            return new Response(false, "Error: El Gerente seleccionado no fue encontrado en la base de datos.");
        }
        
        Publisher nuevaEditorial = new Publisher(nit, nombre, direccion, gerenteSeleccionado);
        
        gerenteSeleccionado.setPublisher(nuevaEditorial);

        MfAddPublisher mfAdd = new MfAddPublisher();
        mfAdd.addPublisher(megaferia, nuevaEditorial);

        return new Response(true, "Editorial " + nombre + " creada con éxito.");
    }
    
}
