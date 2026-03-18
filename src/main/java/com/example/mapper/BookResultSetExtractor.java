package com.example.mapper;

import com.example.model.Author;
import com.example.model.Book;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookResultSetExtractor implements ResultSetExtractor<List<Book>> {

    private final BookRowMapper bookRowMapper;
    private final AuthorRowMapper authorRowMapper;

    @Autowired
    public BookResultSetExtractor(BookRowMapper bookRowMapper, AuthorRowMapper authorRowMapper) {
        this.bookRowMapper = bookRowMapper;
        this.authorRowMapper = authorRowMapper;
    }

    @Override
    public @Nullable List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer,Book> books = new HashMap<>();
        while (rs.next()) {
            var bookId = rs.getInt("b_id");
            Book book = books.getOrDefault(bookId, bookRowMapper.mapRow(rs, rs.getRow()));
            if (rs.getObject("b_id", Long.class) != null) {
                book.setAuthor(authorRowMapper.mapRow(rs, rs.getRow()));
            }
            if (!books.containsKey(bookId)) {
                books.put(bookId, book);
            }
        }
        return books.values().stream().toList();
    }
}
