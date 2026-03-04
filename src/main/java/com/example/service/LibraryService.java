//package com.example.service;
//
//import jakarta.jws.WebService;
//import libraryAPI.dao.BookDAOImpl;
//import libraryAPI.dao.BorrowedBooksDaoImpl;
//import libraryAPI.dao.ReaderDaoImpl;
//import libraryAPI.model.Book;
//import libraryAPI.model.BorrowedBook;
//import libraryAPI.model.Reader;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@WebService(endpointInterface = "com.example.service.LibraryServiceInterface")
//public class LibraryService implements LibraryServiceInterface {
//
//    BorrowedBooksDaoImpl borrowedBooksDao;
//    BookDAOImpl bookDAO;
//    ReaderDaoImpl readerDAO;
//
//    public LibraryService() {
//        this.borrowedBooksDao = new BorrowedBooksDaoImpl();
//        this.bookDAO = new BookDAOImpl();
//        this.readerDAO = new ReaderDaoImpl();
//    }
//
//    @Override
//    public boolean addBook(Book book) {
//        return bookDAO.addBook(book)!=null;
//    }
//
//    @Override
//    public boolean addReader(Reader reader) {
//        return readerDAO.addReader(reader)!=null;
//    }
//
//    @Override
//    public boolean addBorrowedBook(BorrowedBook borrowedBook) {
//        return borrowedBooksDao.addBorrowedBook(borrowedBook)!=null;
//    }
//
//    @Override
//    public List<BorrowedBook> getBorrowedBooksByReaderId(int readerId) {
//        return borrowedBooksDao.getBorrowedByReaderId(readerId);
//    }
//}
