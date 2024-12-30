package com.magazin.Books.Backend.controllers;

import com.magazin.Books.Backend.Entity.Book;
import com.magazin.Books.Backend.Entity.Cart;
import com.magazin.Books.Backend.repository.BookRepository;
import com.magazin.Books.Backend.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/db_book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    private Cart cart = new Cart(); // Создаем корзину на уровне контроллера

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

        @PostMapping
        public ResponseEntity<Book> addOrUpdateBook(@RequestBody Book book) {
            // Сохраняем книгу. Если id не передан (или равен 0), будет создана новая книга
            Book savedBook = bookService.saveBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
            Book book = bookService.getBookById(id);
            if (book == null) {
                return ResponseEntity.notFound().build();
            }

            // Обновляем детали книги
            book.setPrice(bookDetails.getPrice());
            book.setAuthor(bookDetails.getAuthor());
            book.setTitle(bookDetails.getTitle());
            bookService.saveBook(book);
            return ResponseEntity.ok(book);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable int id) {
            if (!bookService.deleteBook(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        }


    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String author,
                                  @RequestParam(required = false) String title) {
        if (author != null) {
            return bookRepository.findByAuthor(author);
        } else if (title != null) {
            return bookRepository.findByTitle(title);
        } else {
            return bookRepository.findAll(); // Возвращаем все книги, если ни автор, ни заглавие не указаны
        }
    }


        @PostMapping("/cart/add")
        public ResponseEntity<String> addBookToCart(@RequestParam(required = false) String author,
                                                    @RequestParam(required = false) String title) {
            List<Book> books;

            if (author != null) {
                books = bookRepository.findByAuthor(author);
            } else if (title != null) {
                books = bookRepository.findByTitle(title);
            } else {
                return ResponseEntity.badRequest().body("Укажите автора или название книги.");
            }

            if (books.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Добавим первую найденную книгу в корзину
            cart.addBook(books.get(0));
            return ResponseEntity.ok("Книга добавлена в корзину: " + books.get(0).toString());
        }

        @GetMapping("/cart")
        public List<Book> getCartBooks() {
            return cart.getBooks();
        }

        @GetMapping("/cart/totalPrice")
        public double getTotalPrice() {
            return cart.getTotalPrice();
        }

    @PostMapping("/cart/checkout")
    public String checkout() {
        if (cart.getBooks().isEmpty()) {
            return "Корзина пуста. Добавьте книги перед оплатой.";
        }

        double totalCost = cart.getBooks().stream().mapToDouble(Book::getPrice).sum();
        // Здесь вы можете добавить логику для обработки платежа

        // После успешной оплаты очищаем корзину
        cart.clear();

        return "Оплата прошла успешно. Итоговая сумма: " + totalCost;
    }
}


