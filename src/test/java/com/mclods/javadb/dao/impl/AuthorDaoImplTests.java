package com.mclods.javadb.dao.impl;

import com.mclods.javadb.TestDataUtils;
import com.mclods.javadb.domain.Author;
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
public class AuthorDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    AuthorDaoImpl authorDaoImpl;

    @Test
    @DisplayName("Test create generates correct sql")
    void testCreateGeneratesCorrectSql() {
        Author author = TestDataUtils.testAuthor();

        authorDaoImpl.create(author);
        verify(jdbcTemplate).update(eq("INSERT INTO author(name, age) VALUES(?, ?)"),
                eq("Abigail Rose"),
                eq((short)80));
    }

    @Test
    @DisplayName("Test findOne generates correct sql")
    void testFindOneGeneratesCorrectSql() {
        Integer id = 1;

        authorDaoImpl.findOne(id);
        verify(jdbcTemplate).query(eq("SELECT id, name, age FROM author WHERE id = ? LIMIT 1"),
                any(AuthorDaoImpl.AuthorRowMapper.class),
                eq(id));
    }
}
