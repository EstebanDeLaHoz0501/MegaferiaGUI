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
public class MfAddPublisher {
    public void addPublisher(IMegaferiaContext mf, Publisher publisher){ 
        mf.getPublishers().add(publisher);
    }
}
