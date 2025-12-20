package com.bookverse.single_file_monolith.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookverse.single_file_monolith.entities.Book;
import com.bookverse.single_file_monolith.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // Repository'yi (Veri deposunu) buraya bağlıyoruz
    //@Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(int id) {
        // İŞ MANTIĞI: ID kontrolü artık burada!
        if (id <= 0) {
            throw new IllegalArgumentException("Kitap ID'si pozitif bir değer olmalıdır.");
        }
        return bookRepository.findById(id);
    }
}