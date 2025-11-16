/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.Stands.Stand;

/**
 *
 * @author Esteban
 */
public class MfAddStand {
    public void addStand(Megaferia mf, Stand stand){
        mf.getStands().add(stand);
    }
}
