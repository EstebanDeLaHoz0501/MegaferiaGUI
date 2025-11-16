/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Megaferia;

import model.Stands.Stand;
import model.Publisher.Publisher;
import model.books.Book;
import java.util.ArrayList;
import model.Person.Author;
import model.Person.Manager;
import model.Person.Narrator;

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
    
    public Megaferia() {
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
