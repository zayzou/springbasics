package com.zayzou.dao;

import com.zayzou.TestDataUtil;
import com.zayzou.dao.impl.AuthorDaoImpl;
import com.zayzou.domain.Author;
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
public class AuthorDaoImplIntegrationTests {

    private final AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        Optional<Author> optionalAuthor = underTest.findById(author.getId());
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        List<Author> authors = TestDataUtil.createTestAuthors();
        authors.forEach(author -> underTest.create(author));

        List<Author> result = underTest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authors.get(0), authors.get(1), authors.get(2));

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        author.setName("UPDATED");
        underTest.update(1L, author);

        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {

        final var ID = 1L;

        //CREATE AUTHORS
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        //DELETE AUTHOR
        underTest.delete(ID);

        Optional<Author> result = underTest.findById(ID);

        assertThat(result).isNotPresent();
        assertThat(result).isEmpty();

    }
}
