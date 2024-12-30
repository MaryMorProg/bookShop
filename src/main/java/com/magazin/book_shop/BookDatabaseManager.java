package com.magazin.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootApplication
public class BookDatabaseManager {

    public static void main(String[] args) {
        SpringApplication.run(BookDatabaseManager.class, args);
    }
}


