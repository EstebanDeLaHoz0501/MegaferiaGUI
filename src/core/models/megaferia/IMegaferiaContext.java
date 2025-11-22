/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.megaferia;

import core.models.book.Book;
import core.models.person.author.Author;
import core.models.person.Manager;
import core.models.person.narrator.Narrator;
import core.models.publisher.Publisher;
import core.models.stand.Stand;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public interface IMegaferiaContext {
    public ArrayList<Stand> getStands();
    public ArrayList<Author> getAuthors();
    public ArrayList<Manager> getManagers();
    public ArrayList<Narrator> getNarrators();
    public ArrayList<Publisher> getPublishers();
    public ArrayList<Book> getBooks();  
     
}
