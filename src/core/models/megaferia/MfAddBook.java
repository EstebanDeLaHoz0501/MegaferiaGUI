/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.megaferia;

import core.models.book.Book;

/**
 *
 * @author Esteban
 */
public class MfAddBook {
    public void addBook(IMegaferiaContext mf, Book book){ 
        mf.getBooks().add(book);
    }
}
