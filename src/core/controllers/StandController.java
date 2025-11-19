/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.util.ArrayList;
import core.models.megaferia.Megaferia;
import core.models.megaferia.MfAddStand;
import core.models.publisher.Publisher;
import core.models.stand.Stand;

/**
 *
 * @author FERNANDO
 */
public class StandController {  
    
    private Megaferia megaferia;
    
    public StandController() {
        this.megaferia = Megaferia.getInstance();
    }
    
    public Response crearStand(String idStr, String precioStr) {
        
        //Validacion (de los 15 digitos totales) -Fernando
        long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: El ID debe ser un número.", Status.BAD_REQUEST);
        }
        
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: El precio debe ser un número.", Status.BAD_REQUEST);
        }

        if (id < 0 || idStr.length() > 15) {
            return new Response(false, "Error: El ID debe ser positivo y de máx 15 dígitos.", Status.BAD_REQUEST);
        }
        
        if (precio <= 0) {
            return new Response(false, "Error: El precio debe ser superior a 0.", Status.BAD_REQUEST);
        }
        
        ArrayList<Stand> listaStands = this.megaferia.getStands();
        for (Stand standExistente : listaStands) {
            if (standExistente.getId() == id) {
                return new Response(false, "Error: El ID del stand ya existe.", Status.BAD_REQUEST);
            }
        }

        Stand nuevoStand = new Stand(id, precio);
        
        MfAddStand mfAdd = new MfAddStand();
        mfAdd.addStand(this.megaferia, nuevoStand);
            
        return new Response(true, "¡Stand " + id + " creado con éxito!", Status.CREATED);
    }
    
    public Response comprarStand(String standsTexto, String editorialesTexto) {
        if (standsTexto.trim().isEmpty() || editorialesTexto.trim().isEmpty()) {
            return new Response(false, "Error: Debe seleccionar al menos un Stand y una Editorial.", Status.BAD_REQUEST);
        }

        String[] standIds = standsTexto.split("\n");
        String[] publisherData = editorialesTexto.split("\n");
        ArrayList<Stand> selectedStands = new ArrayList<>();
        ArrayList<Publisher> selectedPublishers = new ArrayList<>();

        for (String idStr : standIds) {
            if (idStr.trim().isEmpty()) continue;
            try {
                long id = Long.parseLong(idStr);                
                Stand found = null;
                for (Stand s : megaferia.getStands()) {
                    if (s.getId() == id) found = s;
                }
                
                if (found == null) return new Response(false, "Error: Stand ID " + id + " no encontrado.", Status.NOT_FOUND);
                if (selectedStands.contains(found)) return new Response(false, "Error: Stand ID " + id + " seleccionado repetido.", Status.BAD_REQUEST);
                
                selectedStands.add(found);
            } catch (NumberFormatException e) {
            }
        }

        for (String data : publisherData) {
            if (data.trim().isEmpty()) continue;
            try {
                if (!data.contains("(") || !data.contains(")")) continue;
                String nit = data.split("\\(")[1].replace(")", "");
                
                Publisher found = new core.models.megaferia.MfGetPublisher().getPublisher(megaferia, nit);
                
                if (found == null) return new Response(false, "Error: Editorial " + nit + " no encontrada.", Status.NOT_FOUND);
                if (selectedPublishers.contains(found)) return new Response(false, "Error: Editorial " + nit + " seleccionada repetida.", Status.BAD_REQUEST);
                
                selectedPublishers.add(found);
            } catch (Exception e) {
            }
        }
        
        if (selectedStands.isEmpty() || selectedPublishers.isEmpty()) {
             return new Response(false, "Error: No se pudieron procesar los Stands o Editoriales seleccionados.", Status.BAD_REQUEST);
        }

        core.models.stand.StandAddPublisher standAdder = new core.models.stand.StandAddPublisher();
        core.models.publisher.PublisherAddStand pubAdder = new core.models.publisher.PublisherAddStand();

        for (Stand stand : selectedStands) {
            for (Publisher pub : selectedPublishers) {
                if (!stand.getPublishers().contains(pub)) {
                    standAdder.addPublisher(stand, pub);
                    pubAdder.publisherAddBook(pub, stand); 
                }
            }
        }

        return new Response(true, "Compra realizada con éxito. Los stands han sido asignados.", Status.OK); 
}
    }
