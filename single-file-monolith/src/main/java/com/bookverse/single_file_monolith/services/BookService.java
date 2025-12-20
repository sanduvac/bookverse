
package com.bookverse.single_file_monolith.services;

import java.util.List;
import java.util.Optional;

import com.bookverse.single_file_monolith.entities.Book;

public interface BookService {
    Optional<Book> findBookById(int id);
    List<Book> findAllBooks();
}