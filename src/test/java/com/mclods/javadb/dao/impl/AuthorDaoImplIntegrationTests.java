package com.mclods.javadb.dao.impl;

import com.mclods.javadb.TestDataUtils;
import com.mclods.javadb.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AuthorDaoImplIntegrationTests {
    private final AuthorDaoImpl authorDaoImpl;

    @Autowired
    AuthorDaoImplIntegrationTests(AuthorDaoImpl authorDaoImpl) {
        this.authorDaoImpl = authorDaoImpl;
    }

    @Test
    @DisplayName("Test author can be created and recalled")
    void testAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.testAuthor();

        authorDaoImpl.create(author);
        Optional<Author> result = authorDaoImpl.findOne(1);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
}
