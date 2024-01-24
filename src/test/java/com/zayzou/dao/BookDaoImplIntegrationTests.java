package com.zayzou.dao;

import com.zayzou.TestDataUtil;
import com.zayzou.dao.impl.BookDaoImpl;
import com.zayzou.domain.Author;
import com.zayzou.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

    private BookDaoImpl underTest;
    private AuthorDao authorDao;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }


    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthor(author.getId());
        underTest.create(book);
        Optional<Book> optionalBook = underTest.findById(book.getIsbn());
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(book);
    }


    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        List<Book> books = TestDataUtil.createTestBooks();
        books.forEach(book -> underTest.create(book));

        List<Book> bookList = underTest.find();
        assertThat(bookList)
                .hasSize(3)
                .containsExactly(books.getFirst(), books.get(1), books.getLast());

    }


    @Test
    public void testThatBookCanBeUpdated() {

        //CREATE TEST AUTHOR
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        //CREATE TEST BOOK
        Book book = TestDataUtil.createTestBook();
        book.setAuthor(author.getId());
        underTest.create(book);


        //SET BOOK NAME
        book.setName("The art of war");

        //UPDATE BOOK NAME
        underTest.update(book.getIsbn(), book);
        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("The art of war");
    }

    @Test
    public void testThatBookCanBeDeleted() {

        //CREATE TEST AUTHOR
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        //CREATE TEST BOOK
        Book book = TestDataUtil.createTestBook();
        book.setAuthor(author.getId());
        underTest.create(book);

        underTest.delete(book.getIsbn());

        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isEmpty();

    }


}
