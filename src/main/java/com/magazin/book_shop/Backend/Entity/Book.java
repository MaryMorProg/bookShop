package com.magazin.Books.Backend.Entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String author;
    private String title;

    public Book() {} // Нужен для JPA

    public Book(String author,String title, double price) {} //Needed for JPA
    public Book(int id, double price, String author, String title) {
        this.id = id;
        this.price = price;
        this.author = author;
        this.title = title;
    }

    // Getters and setters for id, price, author, title


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


