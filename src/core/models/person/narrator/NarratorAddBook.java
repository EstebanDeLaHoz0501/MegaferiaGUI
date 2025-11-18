/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.person.narrator;

import core.models.person.narrator.Narrator;
import core.models.book.Audiobook;

/**
 *
 * @author kevin
 */
public class NarratorAddBook {
    
    public void addBook(Narrator narrator, Audiobook book){
        narrator.getBooks().add(book);
    }
}
