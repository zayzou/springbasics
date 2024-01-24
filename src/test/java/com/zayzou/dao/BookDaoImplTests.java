package com.zayzou.dao;

import com.zayzou.TestDataUtil;
import com.zayzou.dao.impl.BookDaoImpl;
import com.zayzou.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {

        Book book = TestDataUtil.createTestBook();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO  books (isbn,name,author_id) VALUES ( ?,?,? )"),
                eq("2113lnb"),
                eq("Aloha is boma"),
                eq(1L)

        );
    }

    @Test
    public void testThatFindBookByIdGeneratesCorrectSql() {

        underTest.findById("2113lnb");

        verify(jdbcTemplate).query(
                eq("SELECT * FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("2113lnb")
        );
    }

    @Test
    public void testThatFindMultipleBooksGeneratesCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT isbn,name,author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBook();
        book.setName("The art of war");
        underTest.update(book.getIsbn(), book);
        verify(jdbcTemplate).update(
                "UPDATE books SET isbn=?, name=?, author_id=? WHERE isbn=?",
                book.getIsbn(),
                book.getName(),
                book.getAuthor(),
                book.getIsbn()
        );
    }

    @Test
    public void testThatDeleteGenerateCorrectSql() {
        final var ISBN = "21213bn";
        underTest.delete(ISBN);
        verify(jdbcTemplate).update("delete from books where isbn=?", ISBN);
    }
}