package com.example.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {

    private int id;
    private String name;
    private String country;
    private List<Book> books;

}
