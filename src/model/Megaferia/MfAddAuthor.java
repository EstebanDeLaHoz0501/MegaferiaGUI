/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.Person.Author;

/**
 *
 * @author Esteban
 */
public class MfAddAuthor {
    public void addAuthor(Megaferia mf, Author author){
        mf.getAuthors().add(author);
    }
}
