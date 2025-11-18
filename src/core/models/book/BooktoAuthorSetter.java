/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.book;

import core.models.person.author.Author;
import core.models.person.author.AuthorAddBook;

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
