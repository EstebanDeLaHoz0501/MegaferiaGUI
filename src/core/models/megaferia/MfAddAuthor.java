/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.megaferia;

import core.models.person.author.Author;

/**
 *
 * @author Esteban
 */
public class MfAddAuthor {
    public void addAuthor(IMegaferiaContext mf, Author author){ 
        mf.getAuthors().add(author); 
    }
}
