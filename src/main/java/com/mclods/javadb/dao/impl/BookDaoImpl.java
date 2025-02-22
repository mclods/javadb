package com.mclods.javadb.dao.impl;

import com.mclods.javadb.dao.BookDao;
import com.mclods.javadb.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author_id, isbn) VALUES(?, ?, ?)", book.getTitle(), book.getAuthorId(), book.getIsbn());
    }
}
