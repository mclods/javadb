package com.mclods.javadb.dao;

import com.mclods.javadb.dao.impl.AuthorDaoImpl;
import com.mclods.javadb.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    AuthorDaoImpl authorDaoImpl;

    @Test
    void testCreateAuthorGeneratesCorrectSql() {
        Author author = Author.builder().name("Abigail Rose").age((short)80).build();

        authorDaoImpl.create(author);
        verify(jdbcTemplate).update(eq("INSERT INTO author (name, age) VALUES (?, ?)"), eq("Abigail Rose"), eq((short)80));
    }
}
