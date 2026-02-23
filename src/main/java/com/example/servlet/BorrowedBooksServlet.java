package com.example.servlet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import libraryAPI.dao.BorrowedBooksDAO;
import libraryAPI.dao.BorrowedBooksDaoImpl;
import libraryAPI.model.BorrowedBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/library/borrowedbook")
public class BorrowedBooksServlet extends HttpServlet {
    BorrowedBooksDAO borrowedBooksDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        borrowedBooksDAO = new BorrowedBooksDaoImpl();
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Request Accepted");
        BorrowedBook borrowedBook;
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Processing request");
            borrowedBook = mapper.readValue(req.getReader(), BorrowedBook.class);;
            if (borrowedBook.getBook_id() == null || borrowedBook.getBorrow_date() == null || borrowedBook.getReturn_date() == null || borrowedBook.getStatus() == null || borrowedBook.getReader_id() == null) {
                throw new NullPointerException();
            }
            System.out.println("Saving reader");
            BorrowedBook addedBorrowedBook = borrowedBooksDAO.addBorrowedBook(borrowedBook);
            if (addedBorrowedBook != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(addedBorrowedBook.toString());
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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        ReaderRequest requestData;
        try (PrintWriter out = resp.getWriter()) {
            requestData = mapper.readValue(req.getReader(), ReaderRequest.class);

            if (requestData == null || requestData.reader_id == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("<html><body><h1>Ошибка</h1><p>JSON должен содержать reader_id</p></body></html>");
                return;
            }

            int readerId = requestData.reader_id;
            // Получаем список заимствованных книг из базы
            List<BorrowedBook> borrowedBooks = borrowedBooksDAO.getBorrowedByReaderId(readerId);

            out.print("<html>");
            out.print("<head><title>Список книг читателя</title></head>");
            out.print("<body>");
            out.print("<h1>Карточка читателя #" + readerId + "</h1>");
            out.print("<div>");
            out.print("<h3>Удерживаемые книги:</h3>");

            if (borrowedBooks.isEmpty()) {
                out.print("<p>На данный момент у читателя нет книг.</p>");
            } else {
                out.print("<ul>");
                for (BorrowedBook bb : borrowedBooks) {
                    out.print("<li>");
                    out.print("<strong>ID книги:</strong> " + bb.getBook_id() + " | ");
                    out.print("<strong>Дата выдачи:</strong> " + bb.getBorrow_date() + " | ");
                    out.print("<strong>Статус:</strong> " + bb.getStatus());
                    out.print("</li>");
                }
                out.print("</ul>");
            }

            out.print("</div>");
            out.print("</body>");
            out.print("</html>");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("<html><body><h1>Ошибка</h1><p>ID читателя должен быть числом</p></body></html>");
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ReaderRequest {
        public Integer reader_id;
    }
}
