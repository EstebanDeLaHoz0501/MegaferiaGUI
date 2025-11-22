/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.megaferia;

import core.models.person.Manager;

/**
 *
 * @author Esteban
 */
public class MfAddManager {
    public void addManager(IMegaferiaContext mf, Manager manager){ 
        mf.getManagers().add(manager);
    }
}
