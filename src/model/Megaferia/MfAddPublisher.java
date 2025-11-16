/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.Publisher.Publisher;

/**
 *
 * @author Esteban
 */
public class MfAddPublisher {
    public void addPublisher(Megaferia mf, Publisher publisher){
        mf.getPublishers().add(publisher);
    }
}
