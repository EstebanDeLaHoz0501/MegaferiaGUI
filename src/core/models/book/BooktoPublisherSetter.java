/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.book;

import core.models.publisher.Publisher;
import core.models.publisher.PublisherAddBook;

/**
 *
 * @author Esteban
 */
public class BooktoPublisherSetter {
    public void booktoPublisherSetter(Publisher publisher, Book book){
        PublisherAddBook pab = new PublisherAddBook();            //////Este metodo lo veo super innecesario, puede que se elimine
        pab.publisherAddBook(publisher, book);                    //////ya publisherAddBook lo hace. -Esteban
    }
}
