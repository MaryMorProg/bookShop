package com.magazin.book_shop.Backend.Entity;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<Book> books = new ArrayList<>();
    public Cart() {
        this.books = new ArrayList<>(); // Инициализация списка книг
    }

    public void addBook(Book book) {
        books.add(book); // Добавление книги в корзину
    }

    public void clear() {
        books.clear(); // Очищаем корзину
    }


    public List<Book> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }
}
