package com.zayzou.dao;

import com.zayzou.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findById(long l);

    List<Author> find();

    void update(long id, Author author);

    void delete(long id);
}
