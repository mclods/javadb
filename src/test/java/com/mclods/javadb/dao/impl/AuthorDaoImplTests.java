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
        Author author = TestDataUtils.testAuthorA();

        authorDaoImpl.create(author);
        verify(jdbcTemplate).update(eq("INSERT INTO author(id, name, age) VALUES(?, ?, ?)"),
                eq(author.getId()),
                eq(author.getName()),
                eq(author.getAge()));
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

    @Test
    @DisplayName("Test find generates correct sql")
    void testFindGeneratesCorrectSql() {
        authorDaoImpl.find();
        verify(jdbcTemplate).query(eq("SELECT id, name, age FROM author"), any(AuthorDaoImpl.AuthorRowMapper.class));
    }

    @Test
    @DisplayName("Test update generates correct sql")
    void testUpdateGeneratesCorrectSql() {
        Integer id = 1;
        Author author = TestDataUtils.testAuthorA();

        authorDaoImpl.update(id, author);
        verify(jdbcTemplate).update(eq("UPDATE author SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(author.getId()),
                eq(author.getName()),
                eq(author.getAge()),
                eq(id));
    }
}
