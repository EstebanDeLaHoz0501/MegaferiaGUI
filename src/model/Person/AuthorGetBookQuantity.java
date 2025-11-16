/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Person;

/**
 *
 * @author Esteban
 */
public class AuthorGetBookQuantity {
    public int getBookQuantity(Author au) {
        return au.getBooks().size();
    }
}
