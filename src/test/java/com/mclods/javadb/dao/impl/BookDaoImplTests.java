package com.mclods.javadb.dao.impl;

import com.mclods.javadb.TestDataUtils;
import com.mclods.javadb.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookDaoImpl;

    @Test
    @DisplayName("Test create generates correct sql")
    void testCreateGeneratesCorrectSql() {
        Book book = TestDataUtils.testBookA();

        bookDaoImpl.create(book);
        verify(jdbcTemplate).update(eq("INSERT INTO book(title, isbn, author_id) VALUES(?, ?, ?)"),
                eq("The Shadow in the Attic"),
                eq("978-1-2345-6789-0"),
                eq(1));
    }

    @Test
    @DisplayName("Test findOne generates correct sql")
    void testFindOneGeneratesCorrectSql() {
        String isbn = "978-1-2345-6789-0";

        bookDaoImpl.findOne(isbn);
        verify(jdbcTemplate).query(eq("SELECT title, isbn, author_id FROM book WHERE isbn = ? LIMIT 1"),
                any(BookDaoImpl.BookRowMapper.class),
                eq(isbn));
    }

    @Test
    @DisplayName("Test find generates correct sql")
    void testFindGeneratesCorrectSql() {
        bookDaoImpl.find();
        verify(jdbcTemplate).query(eq("SELECT title, isbn, author_id FROM book"), any(BookDaoImpl.BookRowMapper.class));
    }
}