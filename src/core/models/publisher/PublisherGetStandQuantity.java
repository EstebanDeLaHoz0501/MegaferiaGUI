/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.publisher;

/**
 *
 * @author Esteban
 */
public class PublisherGetStandQuantity {
    public int publisherGetStandQuantity(Publisher publisher){
        return publisher.getStands().size();
    }
}
