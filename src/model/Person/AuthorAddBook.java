/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Person;

import model.books.Book;

/**
 *
 * @author Esteban
 */
public class AuthorAddBook {
    public void addBook(Author au, Book book) {
        au.getBooks().add(book);
    }
}
