/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.megaferia;

import core.models.stand.Stand;
import core.models.publisher.Publisher;
import core.models.book.Book;
import java.util.ArrayList;
import core.models.person.author.Author;
import core.models.person.Manager;
import core.models.person.narrator.Narrator;

/**
 *
 * @author Esteban
 */
public class Megaferia {
    
    private static Megaferia instance;
    private ArrayList<Stand> stands;
    private ArrayList<Author> authors;
    private ArrayList<Manager> managers;
    private ArrayList<Narrator> narrators;
    private ArrayList<Publisher> publishers;
    private ArrayList<Book> books;
    
    private Megaferia() {
        this.stands = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.narrators = new ArrayList<>();
        this.publishers = new ArrayList<>();
        this.books = new ArrayList<>();
    }
    
    public static Megaferia getInstance() {
        if (instance == null) {
            instance = new Megaferia();
        }
        return instance;
    }

    public ArrayList<Stand> getStands() {
        return stands;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public ArrayList<Narrator> getNarrators() {
        return narrators;
    }

    public ArrayList<Publisher> getPublishers() {
        return publishers;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
    
}
