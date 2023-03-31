package com.example.BookApp.service;

import com.example.BookApp.model.Book;
import com.example.BookApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repo;
    @Override
    public double total_price() {
        List<Book> books = repo.findAll();
        double totalPrice = 0.0;
        for (Book book : books) {
            totalPrice += book.getPrice();
        }

        return totalPrice;
    }

}
