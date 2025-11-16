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
public class MfGetAuthor {
    public Author getAuthor(Megaferia mf, int id){
        for (Author auth : mf.getAuthors()) {
            if (auth.getId() == id) { /////////////MfGetAuthor
                return auth;
            }
        }
        return null;
    }
}
