//package com.example.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet({"/library/*"})
//public class LibraryServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        String pathInfo = req.getPathInfo(); // Получаем часть пути после /library
//
//        if (pathInfo == null || pathInfo.equals("/")) {
//            resp.getWriter().write("Welcome to the Library!");
//            return;
//        }
//
//        // Логика перенаправления (Forward)
//        switch (pathInfo) {
//            case "/books":
//                req.getRequestDispatcher("/books").forward(req, resp);
//                break;
//            case "/readers":
//                req.getRequestDispatcher("/readers").forward(req, resp);
//                break;
//            default:
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found in library");
//                break;
//        }
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        String pathInfo = req.getPathInfo(); // Получаем часть пути после /library
//
//        if (pathInfo == null || pathInfo.equals("/")) {
//            resp.getWriter().write("Welcome to the Library!");
//            return;
//        }
//
//        // Логика перенаправления (Forward)
//        switch (pathInfo) {
//            case "/add_books":
//                req.getRequestDispatcher("/add_books").forward(req, resp);
//                break;
//            case "/add_readers":
//                req.getRequestDispatcher("/add_readers").forward(req, resp);
//                break;
//            case "/borrowedbook":
//                req.getRequestDispatcher("/borrowedbook").forward(req, resp);
//                break;
//            default:
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found in library");
//                break;
//        }
//    }
//}


