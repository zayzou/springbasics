package com.zayzou.dao;

import com.zayzou.TestDataUtil;
import com.zayzou.dao.impl.AuthorDaoImpl;
import com.zayzou.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorDaoImplTests {


    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id,name,age) VALUES ( ?,?,? )"),
                eq(1L),
                eq("Zayzou"),
                eq(30)
        );
    }


    @Test
    public void testThatGetAuthorByIdGeneratesCorrectSql() {
        underTest.findById(1L);
        verify(jdbcTemplate).query(
                eq("SELECT * FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(1L, author);
        verify(jdbcTemplate).update(
                "UPDATE authors SET id=?, name=?, age=? WHERE id=?",
                1L, "Zayzou", 30, 1L
        );
    }

    @Test
    public void testThatDeleteAuthorGeneratesCorrectSql() {
        final var ID = 1L;
        underTest.delete(ID);
        verify(jdbcTemplate)
                .update("delete from authors where id=?", ID);

    }
}