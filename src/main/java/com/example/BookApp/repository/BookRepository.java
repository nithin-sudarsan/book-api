package com.example.BookApp.repository;

import com.example.BookApp.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String> {
    List<Book> findByAuthor(String author);
}
