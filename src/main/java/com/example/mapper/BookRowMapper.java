package com.example.mapper;

import com.example.model.Author;
import com.example.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setId(rs.getInt("b_id"));
        book.setTitle(rs.getString("b_title"));
        book.setPublishedYear(rs.getInt("b_published_year"));
        book.setAuthorId(rs.getInt("b_author_id"));

        return book;
    }
}
