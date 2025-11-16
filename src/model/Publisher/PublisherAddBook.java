/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Publisher;

import model.books.Book;

/**
 *
 * @author Esteban
 */
public class PublisherAddBook {
    public void publisherAddBook(Publisher publisher, Book book){
        publisher.getBooks().add(book);
    }
}
