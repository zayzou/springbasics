package com.zayzou.repositories;

import com.zayzou.TestDataUtil;
import com.zayzou.domain.Customer;
import com.zayzou.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryIntegrationTests {

    private final ProductRepository underTest;

    @Autowired
    public ProductRepositoryIntegrationTests(ProductRepository underTest) {
        this.underTest = underTest;
    }


    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        Customer customer = TestDataUtil.createTestCustomer();
        Product product = new Product(1L, "Iphone 11", customer);

        underTest.save(product);
        Optional<Product> optionalBook = underTest.findById(product.getId());
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(product);
    }



/*
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
*/


}
