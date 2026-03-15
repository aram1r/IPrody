package com.iprody.library.entity;


import jakarta.persistence.*;

import java.sql.Date;

@Table(name = "borrowed_books", schema = "\"Library\"")
@Entity
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrow_id")
    private Long id;

    @Column(name="borrow_date")
    private Date borrowDate;

    @Column(name="return_date")
    private Date returnDate;

    @Column(name="status")
    private String status;

    @OneToOne
    @JoinColumn(name="book_id")
    private Book book;

    @OneToOne
    @JoinColumn(name="reader_id")
    private Reader reader;

    public BorrowedBook() {
    }

    public BorrowedBook(Date borrowDate, Date returnDate, String status, Book book, Reader reader) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.book = book;
        this.reader = reader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                ", book=" + book +
                ", reader=" + reader +
                '}';
    }
}
