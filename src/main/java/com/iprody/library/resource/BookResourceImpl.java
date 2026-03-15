package com.iprody.library.resource;

import com.iprody.library.entity.Book;
import com.iprody.library.entity.Reader;
import com.iprody.library.repository.BookRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;


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

    @GET
    @Path("/findborrowedbooksbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Book> getBorrowedBooksById(@PathParam("id") String id) {
        try {
            Long requestId = Long.parseLong(id);
            if (requestId >= 0) {
                return bookRepository.findBorrowedBooksByReaderId(requestId);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}