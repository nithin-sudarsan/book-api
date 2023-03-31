package com.example.BookApp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document(collection = "Book")
public class Book {
    @Id
    @GeneratedValue()
    private String id;

    @Schema(description = "Title of the book", example = "Atomic Habits", format = "String")
    @Length(min=1)
    private String title;

    @Schema(description = "Author of the book", example = "James Clear", format = "String")
    @Length(min=1)
    private String author;

    @Schema(description = "Price of the book", example = "199", format = "Integer")
    private double price;

    @Schema(description = "Book Category", example = "Self-help", format = "String")
    private String category;
}
