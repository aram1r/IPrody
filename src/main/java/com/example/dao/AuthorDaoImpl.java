package com.example.dao;

import com.example.mapper.AuthorResultSetExtractor;
import com.example.mapper.AuthorRowMapper;
import com.example.model.Author;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;
    private final AuthorResultSetExtractor authorResultSetExtractor;

    @Autowired
    AuthorDaoImpl(DataSource dataSource, AuthorResultSetExtractor authorResultSetExtractor) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.authorResultSetExtractor = authorResultSetExtractor;
    }

    @Override
    public void create(Author author) {
        try {
            jdbcTemplate.update("INSERT INTO books.authors(name,country) VALUES(?,?)", author.getName(), author.getCountry());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query("select a.id as a_id, a.name as a_name, a.country as a_country, b.id as b_id, b.title as b_title, b.published_year as b_published_year, b.author_id as b_author_id " +
                "from books.authors a left join books.books b on a.id=b.author_id", authorResultSetExtractor);
    }

    @Override
    public void update(Author author) {
        try {
            jdbcTemplate.update("UPDATE books.authors SET name = ?, country = ? WHERE id = ?",  author.getName(), author.getCountry(), author.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Author author) {
        try {
            jdbcTemplate.update("DELETE FROM books.authors WHERE id = ?", author.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author findById(int id) {
//        return jdbcTemplate.queryForObject("select * from books.authors where id=?", new BeanPropertyRowMapper<>(Author.class), id);
        List<Author> authors = jdbcTemplate.query("select a.id as a_id, a.name as a_name, a.country as a_country, b.id as b_id, b.title as b_title, b.published_year as b_published_year, b.author_id as b_author_id " +
                "from books.authors a left join books.books b on b.author_id=a.id where a.id=?", authorResultSetExtractor, id);
        if (authors==null || authors.isEmpty()) {
            return null;
        }

        if (authors.size()>1) {
            throw new RuntimeException("More than one author found with id: " + id);
        }

        return authors.getFirst();
    }

    @Override
    public void create(List<Author> authors) {
        jdbcTemplate.batchUpdate("INSERT INTO books.authors(name,country) VALUES(?,?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, authors.get(i).getName());
                ps.setString(2, authors.get(i).getCountry());
            }

            @Override
            public int getBatchSize() {
                return authors.size();
            }
        });
    }

}
