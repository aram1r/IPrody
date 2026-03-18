package com.example.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String title;
    private int authorId;
    private int publishedYear;
    private Author author;
}
