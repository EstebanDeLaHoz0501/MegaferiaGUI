/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.person.narrator;

import core.models.book.Audiobook;
import core.models.person.Person;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Narrator extends Person {
    
    private ArrayList<Audiobook> books;

    public Narrator(long id, String firstname, String lastname) {
        super(id, firstname, lastname);
        this.books = new ArrayList<>();
    }
    
    public ArrayList<Audiobook> getBooks() {
        return books;
    }
    
    //public int getBookQuantity() {
        //return this.books.size();
    //}
    
}
