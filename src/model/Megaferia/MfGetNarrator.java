/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.Person.Narrator;

/**
 *
 * @author Esteban
 */
public class MfGetNarrator {
    public Narrator getNarrator(Megaferia mf, long id){     ///////////////// Cambie el dato recibido de int a long porque int son 10 digitos y el formulario pidio 15 digitos maximo - Fernando
        for (Narrator narrat : mf.getNarrators()) {
            if (narrat.getId() == id) {       
                return narrat;
            }
        }
        return null;
    }
}
