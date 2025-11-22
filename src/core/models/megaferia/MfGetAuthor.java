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
public class MfGetAuthor {
    public Author getAuthor(IMegaferiaContext mf, long id){ 
        for (Author auth : mf.getAuthors()) {
            if (auth.getId() == id) return auth;
        }
        return null;
    }
}
