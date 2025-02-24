package com.mclods.javadb.dao;

import com.mclods.javadb.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne(Integer authorId);
}
