package com.iprody.library.entity;


import jakarta.persistence.*;

@Table(name = "books", schema = "\"Library\"")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name = "published_year")
    private Integer published_year;

    @Column(name = "genre")
    private String genre;

    @OneToOne(mappedBy = "book")
    private BorrowedBook borrowedBook;

    public Book() {}

    public Book(String title, String author, Integer published_year, String genre) {
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublished_year() {
        return published_year;
    }

    public void setPublished_year(Integer published_year) {
        this.published_year = published_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published_year=" + published_year +
                ", genre='" + genre + '\'' +
                '}';
    }
}
