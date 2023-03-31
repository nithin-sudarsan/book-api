package com.example.BookApp.controller;


import com.example.BookApp.model.Book;
import com.example.BookApp.repository.BookRepository;
import com.example.BookApp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book-api")
public class BookController {
    @Autowired
    private BookRepository repo;
    @Autowired
    private BookService bookService;

    @GetMapping("/book-by-author/")
    @Operation(summary = "Get a book by Author", description = "Returns a list of books written by the given author ")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(value="name", required = true) String author)
    {
        List<Book> books=repo.findByAuthor(author);
        if(books!=null)
            return ResponseEntity.ok().body((List<Book>) books);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    @Operation(summary = "Get all Books", description = "Returns a list of all books")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public List<Book> getAllBooks()
    {
        return repo.findAll();
    }
    @PostMapping("/")
    @Operation(summary = "Add Book", description = "Adds a book to the list of books")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public Book addBook(Book book)
    {
        return repo.save(book);
    }
    @PutMapping("/update-book/{author}")
    @Operation(summary = "Update Book", description = "Updates a book written by author ")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public Book updateBook(@RequestParam(value="name", required = true) String author, @RequestBody Book book)
    {
        book.setAuthor(author);
        return repo.save(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Delete book that has given Id")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public void deleteBooks(@PathVariable String id)
    {
        repo.deleteById(id);
    }
    @GetMapping("/total-price")
    @Operation(summary = "Total Price", description = "Returns total cost of all books in the list")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    public double total_amount()
    {
        return bookService.total_price();
    }
}
