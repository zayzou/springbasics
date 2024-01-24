package com.zayzou.dao;

import com.zayzou.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findById(String isbn);

    List<Book> find();

    void update(String isbn, Book book);

    void delete(String isbn);
}
