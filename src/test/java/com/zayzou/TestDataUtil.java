package com.zayzou;

import com.zayzou.domain.Author;
import com.zayzou.domain.Book;
import com.zayzou.model.Customer;
import com.zayzou.model.Product;

import java.util.List;

public final class TestDataUtil {

    private TestDataUtil() {
        throw new AssertionError("Cannot be instantiated !");
    }

    public static Author createTestAuthor() {
        return new Author(1L, "Zayzou", 30);
    }

    public static Customer createTestCustomer() {
        return new Customer(1L, "Zayzou", 30);
    }

    public static List<Author> createTestAuthors() {
        return List.of(
                new Author(1L, "Zayzou", 30),
                new Author(2L, "Hazenberg", 65),
                new Author(3L, "Jesse", 30)
        );
    }

    public static Book createTestBook() {
        return new Book("2113lnb", "Aloha is boma", 1L);
    }


    public static List<Book> createTestBooks() {
        return List.of(
                new Book("2113lnb", "Aloha is boma", 1L),
                new Book("2113lab", "Aloha is boma", 1L),
                new Book("2113llb", "Aloha is boma", 1L)
        );
    }
}
