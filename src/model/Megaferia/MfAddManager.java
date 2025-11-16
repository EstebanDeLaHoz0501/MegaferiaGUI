/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.Person.Manager;

/**
 *
 * @author Esteban
 */
public class MfAddManager {
    public void addManager(Megaferia mf, Manager manager){
        mf.getManagers().add(manager);
    }
}
