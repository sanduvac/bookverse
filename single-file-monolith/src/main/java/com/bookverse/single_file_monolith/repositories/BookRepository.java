package com.bookverse.single_file_monolith.repositories;

import java.util.List;
import java.util.Optional;

import com.bookverse.single_file_monolith.entities.Book;

public interface BookRepository {
    Optional<Book> findById(int id);
    List<Book> findAll();
}