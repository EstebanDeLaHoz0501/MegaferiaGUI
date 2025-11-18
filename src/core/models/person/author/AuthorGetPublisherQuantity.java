/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.person.author;

//import core.models.person.author.Author;
import java.util.ArrayList;
import core.models.publisher.Publisher;
import core.models.book.Book;

/**
 *
 * @author Esteban
 */
public class AuthorGetPublisherQuantity {
    public int getPublisherQuantity(Author au) {
        ArrayList<Publisher> publishers = new ArrayList<>();
        for (Book book : au.getBooks()) {
            if (!publishers.contains(book.getPublisher())) {
                publishers.add(book.getPublisher());
            }
        }
        return publishers.size();
    }
}
