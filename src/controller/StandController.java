/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import java.util.ArrayList;
import model.Megaferia.Megaferia;
import model.Megaferia.MfAddStand;
import model.Stands.Stand;

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
            return new Response(false, "Error: El ID debe ser un número.");
        }
        
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            return new Response(false, "Error: El precio debe ser un número.");
        }

        if (id < 0 || idStr.length() > 15) {
            return new Response(false, "Error: El ID debe ser positivo y de máx 15 dígitos.");
        }
        
        if (precio <= 0) {
            return new Response(false, "Error: El precio debe ser superior a 0.");
        }
        
        ArrayList<Stand> listaStands = this.megaferia.getStands();
        for (Stand standExistente : listaStands) {
            if (standExistente.getId() == id) {
                return new Response(false, "Error: El ID del stand ya existe.");
            }
        }

        Stand nuevoStand = new Stand(id, precio);
        
        MfAddStand mfAdd = new MfAddStand();
        mfAdd.addStand(this.megaferia, nuevoStand);
            
        return new Response(true, "¡Stand " + id + " creado con éxito!");
    }

    
    
}
