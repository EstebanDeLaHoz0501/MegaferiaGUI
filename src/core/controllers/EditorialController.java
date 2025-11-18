/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.util.ArrayList;
import core.models.megaferia.Megaferia;
import core.models.megaferia.MfAddPublisher;
import core.models.person.Manager;
import core.models.publisher.Publisher;

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
            return new Response(false, "Error: Los campos NIT, Nombre y Dirección son obligatorios.", Status.BAD_REQUEST);
        }

        String nitPattern = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{1}";
        if (!nit.matches(nitPattern)) {
            return new Response(false, "Error: El NIT no cumple con el formato XXX.XXX.XXX-X (Ej: 900.123.456-7).", Status.BAD_REQUEST);
        }

        ArrayList<Publisher> listaEditoriales = this.megaferia.getPublishers();
        for (Publisher editorialExistente : listaEditoriales) {
            if (editorialExistente.getNit().equals(nit)) {
                return new Response(false, "Error: El NIT de la editorial ya existe.", Status.BAD_REQUEST);
            }
        }
        
        if (managerStr == null || managerStr.equals("Seleccione uno...")) {
            return new Response(false, "Error: Debe seleccionar un Gerente válido.", Status.BAD_REQUEST);
        }
        
        long managerId;
        Manager gerenteSeleccionado = null;
        try {
            String[] managerData = managerStr.split(" - ");
            managerId = Long.parseLong(managerData[0]);
        } catch (Exception e) {
            return new Response(false, "Error: El formato del Gerente seleccionado es inválido.", Status.BAD_REQUEST);
        }
        
        for (Manager manager : this.megaferia.getManagers()) {
            if (manager.getId() == managerId) {
                gerenteSeleccionado = manager;
                break;
            }
        }

        if (gerenteSeleccionado == null) {
            return new Response(false, "Error: El Gerente seleccionado no fue encontrado en la base de datos.", Status.NOT_FOUND);
        }
        
        Publisher nuevaEditorial = new Publisher(nit, nombre, direccion, gerenteSeleccionado);
        
        gerenteSeleccionado.setPublisher(nuevaEditorial);

        MfAddPublisher mfAdd = new MfAddPublisher();
        mfAdd.addPublisher(megaferia, nuevaEditorial);

        return new Response(true, "Editorial " + nombre + " creada con éxito.", Status.CREATED);
    }
    
}
