package com.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import libraryAPI.dao.BookDAOImpl;
import libraryAPI.dao.ReaderDaoImpl;
import libraryAPI.model.Book;
import libraryAPI.model.Reader;

import java.io.IOException;

@WebServlet("/library/add_readers")
public class ReadersServlet extends HttpServlet {

    ReaderDaoImpl readerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        readerDAO = new ReaderDaoImpl();
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Request Accepted");
        Reader reader;
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Processing request");
            reader = mapper.readValue(req.getReader(), Reader.class);
            if (reader.getEmail() == null || reader.getPhone() == null || reader.getName() == null) {
                throw new NullPointerException();
            }
            System.out.println("Saving reader");
            Reader addedReader = readerDAO.addReader(reader);
            if (addedReader != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(addedReader.toString());
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
