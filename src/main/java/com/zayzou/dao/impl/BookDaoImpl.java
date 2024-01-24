package com.zayzou.dao.impl;


import com.zayzou.dao.BookDao;
import com.zayzou.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    final private JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {

        jdbcTemplate.update(
                "INSERT INTO  books (isbn,name,author_id) VALUES ( ?,?,? )",
                book.getIsbn(),
                book.getName(),
                book.getAuthor()
        );
    }

    @Override
    public Optional<Book> findById(String id) {
        List<Book> result = jdbcTemplate.query(
                "SELECT * FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                id
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query("SELECT isbn,name,author_id FROM books", new BookRowMapper());
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update("UPDATE books SET isbn=?, name=?, author_id=? WHERE isbn=?",
                book.getIsbn(),
                book.getName(),
                book.getAuthor(),
                isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("delete from books where isbn=?", isbn);
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getString("isbn"),
                    rs.getString("name"),
                    rs.getLong("author_id")
            );
        }


    }

}
