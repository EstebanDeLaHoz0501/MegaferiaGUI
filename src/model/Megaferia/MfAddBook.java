/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.books.Book;

/**
 *
 * @author Esteban
 */
public class MfAddBook {
    public void addBook(Megaferia mf, Book book){
        mf.getBooks().add(book);
    }
}
