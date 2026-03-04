package com.example.resource;

import libraryAPI.model.BorrowedBook;

import java.util.List;

public interface BorrowedBookResourceInterface {
    BorrowedBook addBorrowedBook(BorrowedBook book);
    List<BorrowedBook> getBorrowedBooksByReaderId(Integer readerId);
}
