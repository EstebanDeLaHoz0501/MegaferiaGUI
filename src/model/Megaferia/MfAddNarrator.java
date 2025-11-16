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
public class MfAddNarrator {
    public void addNarrator(Megaferia mf, Narrator narrator){
        mf.getNarrators().add(narrator);
    }
}
