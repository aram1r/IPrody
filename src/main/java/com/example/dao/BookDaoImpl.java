package com.example.dao;

import com.example.mapper.BookResultSetExtractor;
import com.example.mapper.BookRowMapper;
import com.example.model.Author;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;
    private final BookResultSetExtractor bookResultSetExtractor;

    @Autowired
    public BookDaoImpl(DataSource dataSource, BookResultSetExtractor bookResultSetExtractor1) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.bookResultSetExtractor = bookResultSetExtractor1;
    }

    @Override
    public void create(Book book) {
        try {
            jdbcTemplate.update("INSERT INTO books.books (title, published_year, author_id) VALUES(?,?,?)", book.getTitle(), book.getPublishedYear(), book.getAuthorId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try {
            jdbcTemplate.update("UPDATE books.books SET title = ?, published_year = ?, author_id = ? WHERE id = ?",  book.getTitle(), book.getPublishedYear(), book.getAuthorId(), book.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            jdbcTemplate.update("DELETE FROM books.books WHERE id = ?", id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findByAuthorId(int id) {;
        return jdbcTemplate.query("select b.id as b_id, b.title as b_title, b.published_year as b_published_year, b.author_id as b_author_id, " +
                "a.id as a_id, a.name as a_name, a.country as a_country from books.books b left join books.authors a on b.author_id=a.id where a.id=?", bookResultSetExtractor, id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select a.id as a_id, a.name as a_name, a.country as a_country, b.id as b_id, b.title as b_title, b.published_year as b_published_year, b.author_id as b_author_id " +
                "from books.authors a left join books.books b on a.id=b.author_id", bookResultSetExtractor);
    }
}
