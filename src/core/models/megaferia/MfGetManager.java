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
public class MfGetManager {
    public Manager getManager(IMegaferiaContext mf, long id){ 
        for (Manager manag : mf.getManagers()) {
            if (manag.getId() == id) return manag;
        }
        return null;
    }
}
