/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.stand;

import core.models.publisher.Publisher;

/**
 *
 * @author Esteban
 */
public class StandAddPublisher {
    public void addPublisher(Stand stand, Publisher publisher){
        stand.getPublishers().add(publisher);
    }
}
