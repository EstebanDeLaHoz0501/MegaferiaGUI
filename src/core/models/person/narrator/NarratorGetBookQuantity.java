/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.person.narrator;

import core.models.person.narrator.Narrator;

/**
 *
 * @author kevin
 */
public class NarratorGetBookQuantity {
    
    public int getBookQuantity(Narrator narrator){
        return narrator.getBooks().size();
    }
}
