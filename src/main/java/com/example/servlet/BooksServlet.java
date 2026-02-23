package com.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import libraryAPI.dao.BookDAOImpl;
import libraryAPI.model.Book;
import java.io.IOException;

@WebServlet({"/library/add_books"})
public class BooksServlet extends jakarta.servlet.http.HttpServlet {

    BookDAOImpl bookDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDAO = new BookDAOImpl();
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Request Accepted");
        Book book;
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Processing request");
            book = mapper.readValue(req.getReader(), Book.class);
            if (book.getAuthor() == null ||  book.getGenre() == null || book.getTitle() == null || book.getPublished_year() == null) {;
                throw new NullPointerException();
            }
            System.out.println("Saving book");
            Book addedBook = bookDAO.addBook(book);
            if (addedBook != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(addedBook.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("Один из параметров не задан");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Параметры заданы неверно");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("Error: " + e.getClass().getName());
        }
    }
}
