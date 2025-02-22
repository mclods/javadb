package com.mclods.javadb.dao.impl;

import com.mclods.javadb.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

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
        Book book = Book.builder().isbn("978-1-2345-6789-0").title("The Shadow in the Attic").authorId(1).build();

        bookDaoImpl.create(book);
        verify(jdbcTemplate).update(eq("INSERT INTO book(title, author_id, isbn) VALUES(?, ?, ?)"), eq("The Shadow in the Attic"), eq(1), eq("978-1-2345-6789-0"));
    }
}