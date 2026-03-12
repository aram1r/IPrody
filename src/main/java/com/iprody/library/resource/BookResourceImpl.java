package com.iprody.library.resource;

import com.iprody.library.entity.Book;
import com.iprody.library.repository.BookRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/books")
public class BookResourceImpl implements BookResourceInterface {

    BookRepository bookRepository;

    public BookResourceImpl() {
        System.out.println("==== BookResourceImpl инициализирован ====");
        bookRepository = new BookRepository();
    }

    @POST
    @Path("/addbook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}