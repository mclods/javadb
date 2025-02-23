package com.mclods.javadb.dao;

import com.mclods.javadb.domain.Book;

import java.util.List;

public interface BookDao {
    void create(Book book);
    List<Book> findOne(String isbn);
}
