/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.stand;


/**
 *
 * @author Esteban
 */
public class StandGetPublisherQuantity {
    public int getPublisherQuantity(Stand stand){
        return stand.getPublishers().size();
    }
}
