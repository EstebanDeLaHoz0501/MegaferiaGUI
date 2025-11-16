/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.books;

import model.Person.Author;
import model.Person.AuthorAddBook;

/**
 *
 * @author Esteban
 */
public class BooktoAuthorSetter {
    AuthorAddBook authorAddBook = new AuthorAddBook();
    public void booktoAuthorSetter(Book book){
        for (Author autor : book.getAuthors()) {
            authorAddBook.addBook(autor, book);                
        }
    }
}
