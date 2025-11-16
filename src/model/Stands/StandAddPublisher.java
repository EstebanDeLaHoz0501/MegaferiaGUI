/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Stands;

import model.Publisher.Publisher;

/**
 *
 * @author Esteban
 */
public class StandAddPublisher {
    public void addPublisher(Stand stand, Publisher publisher){
        stand.getPublishers().add(publisher);
    }
}
