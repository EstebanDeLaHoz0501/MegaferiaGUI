/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.megaferia;

import core.models.publisher.Publisher;

/**
 *
 * @author Esteban
 */
public class MfGetPublisher {
        public Publisher getPublisher(Megaferia mf, String nit){
        for (Publisher publish : mf.getPublishers()) {   
            if(publish.getNit().equals(nit)){   
                return publish;               
            }                                  
        }
        return null;
    }
}
