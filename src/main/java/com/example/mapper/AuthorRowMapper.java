package com.example.mapper;

import com.example.model.Author;
import com.example.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

        Author author = new Author();
        author.setId(rs.getInt("a_id"));
        author.setName(rs.getString("a_name"));
        author.setCountry(rs.getString("a_country"));

        return author;
    }
}
