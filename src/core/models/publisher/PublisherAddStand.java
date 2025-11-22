/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.publisher;

import core.models.stand.Stand;



/**
 *
 * @author Esteban
 */
public class PublisherAddStand {
    public void publisherAddStand(Publisher publisher, Stand stand){
        publisher.getStands().add(stand); 
    }
}
