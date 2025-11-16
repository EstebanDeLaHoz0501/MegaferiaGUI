/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Publisher;

import model.Stands.Stand;



/**
 *
 * @author Esteban
 */
public class PublisherAddStand {
    public void publisherAddBook(Publisher publisher, Stand stand){
        publisher.getStands().add(stand);
    }
}
