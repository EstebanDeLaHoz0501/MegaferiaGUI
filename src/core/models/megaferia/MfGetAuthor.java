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
    public Author getAuthor(Megaferia mf, long id){    ///////////////// Cambie el dato recibido de int a long porque int son 10 digitos y el formulario pidio 15 digitos maximo - Fernando
        for (Author auth : mf.getAuthors()) {
            if (auth.getId() == id) { /////////////MfGetAuthor
                return auth;
            }
        }
        return null;
    }
}
