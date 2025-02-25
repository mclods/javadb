package com.mclods.javadb.dao;

import com.mclods.javadb.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String isbn);
    List<Book> find();
}
