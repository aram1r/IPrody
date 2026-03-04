package com.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import libraryAPI.dao.BookDAOImpl;
import libraryAPI.dao.BorrowedBooksDAO;
import libraryAPI.dao.BorrowedBooksDaoImpl;
import libraryAPI.model.BorrowedBook;

import java.util.ArrayList;
import java.util.List;

@Path("/borrowed_books")
public class BorrowedBookResourceImpl implements BorrowedBookResourceInterface {

    BorrowedBooksDAO borrowedBooksDAO;

    public BorrowedBookResourceImpl() {
        borrowedBooksDAO = new BorrowedBooksDaoImpl();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public BorrowedBook addBorrowedBook(BorrowedBook book) {
        try {
            return borrowedBooksDAO.addBorrowedBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/get/{readerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<BorrowedBook> getBorrowedBooksByReaderId(@PathParam("readerId") Integer readerId) {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        try {
            borrowedBooks = borrowedBooksDAO.getBorrowedByReaderId(readerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }
}
