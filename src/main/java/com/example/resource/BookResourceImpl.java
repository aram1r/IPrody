package com.example.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import libraryAPI.dao.BookDAO;
import libraryAPI.dao.BookDAOImpl;
import libraryAPI.model.Book;

@Path("/books")
public class BookResourceImpl implements BookResourceInterface {

    BookDAO bookDAO;

    public BookResourceImpl() {
        System.out.println("==== BookResourceImpl инициализирован ====");
        bookDAO = new BookDAOImpl();
    }

    @POST
    @Path("/addbook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Book addBook(Book book) {
        try {
            return bookDAO.addBook(book);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
