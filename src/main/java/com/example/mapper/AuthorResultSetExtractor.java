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
public class AuthorResultSetExtractor implements ResultSetExtractor<List<Author>> {

    private final BookRowMapper bookRowMapper;
    private final AuthorRowMapper authorRowMapper;

    @Autowired
    public AuthorResultSetExtractor(BookRowMapper bookRowMapper, AuthorRowMapper authorRowMapper) {
        this.bookRowMapper = bookRowMapper;
        this.authorRowMapper = authorRowMapper;
    }

    @Override
    public @Nullable List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer,Author> authors = new HashMap<>();
        while (rs.next()) {
            var authorId = rs.getInt("a_id");
            Author author = authors.getOrDefault(authorId, authorRowMapper.mapRow(rs, rs.getRow()));
            if (rs.getObject("a_id", Long.class) != null) {
                if (author.getBooks() == null) {
                    author.setBooks(new ArrayList<>());
                }
                author.getBooks().add(bookRowMapper.mapRow(rs, rs.getRow()));
            }
            if (!authors.containsKey(authorId)) {
                authors.put(authorId, author);
            }
        }
        return authors.values().stream().toList();
    }
}
