/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.publisher;

import core.models.book.Book;

/**
 *
 * @author Esteban
 */
public class PublisherAddBook {
    public void publisherAddBook(Publisher publisher, Book book){
        publisher.getBooks().add(book);
    }
}
