package com.bookverse.single_file_monolith.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookverse.single_file_monolith.entities.Book;
import com.bookverse.single_file_monolith.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;

    // Service'i (İş mantığını) buraya bağlıyoruz
   // @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable int id) {
        try {
            // Veriyi Service'ten istiyoruz
            return bookService.findBookById(id)
                    .<ResponseEntity<Object>>map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            // Hata olursa (örneğin ID -1 ise) burası yakalıyor
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}