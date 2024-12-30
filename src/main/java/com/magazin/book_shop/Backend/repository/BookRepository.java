package com.magazin.book_shop.Backend.repository;


import com.magazin.book_shop.Backend.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
        List<Book> findByAuthor(String author);
        List<Book> findByTitle(String title);

}

