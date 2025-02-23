package com.mclods.javadb.dao;

import com.mclods.javadb.domain.Author;

import java.util.List;

public interface AuthorDao {
    void create(Author author);
    List<Author> findOne(Integer authorId);
}
