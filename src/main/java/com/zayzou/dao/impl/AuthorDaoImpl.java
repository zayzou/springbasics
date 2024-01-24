package com.zayzou.dao.impl;

import com.zayzou.dao.AuthorDao;
import com.zayzou.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {


    final private JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors (id,name,age) VALUES ( ?,?,? )",
                author.getId(), author.getName(), author.getAge()
        );
    }

    @Override
    public Optional<Author> findById(long id) {
        List<Author> result = jdbcTemplate.query(
                "SELECT * FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(),
                id
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Author> find() {
        return jdbcTemplate.query("SELECT id,name,age FROM authors", new AuthorRowMapper());

    }

    @Override
    public void update(long id, Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET id=?, name=?, age=? WHERE id=?",
                author.getId(), author.getName(), author.getAge(), id
        );

    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from authors where id=?", id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
        }
    }
}
