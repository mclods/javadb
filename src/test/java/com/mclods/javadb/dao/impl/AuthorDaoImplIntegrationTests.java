package com.mclods.javadb.dao.impl;

import com.mclods.javadb.TestDataUtils;
import com.mclods.javadb.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class AuthorDaoImplIntegrationTests {
    private final AuthorDaoImpl authorDaoImpl;

    @Autowired
    AuthorDaoImplIntegrationTests(AuthorDaoImpl authorDaoImpl) {
        this.authorDaoImpl = authorDaoImpl;
    }

    @Test
    @DisplayName("Test author can be created and recalled")
    void testAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.testAuthorA();

        authorDaoImpl.create(author);
        Optional<Author> result = authorDaoImpl.findOne(1);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    @DisplayName("Test multiple authors can be created and recalled")
    void testMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtils.testAuthorA();
        Author authorB = TestDataUtils.testAuthorB();
        Author authorC = TestDataUtils.testAuthorC();

        authorDaoImpl.create(authorA);
        authorDaoImpl.create(authorB);
        authorDaoImpl.create(authorC);

        List<Author> result = authorDaoImpl.find();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorA, authorB, authorC);
    }

    @Test
    @DisplayName("Test author can be updated")
    void testAuthorCanBeUpdated() {
        Author author = TestDataUtils.testAuthorA();
        Integer id = author.getId();

        authorDaoImpl.create(author);

        // Updated Author
        Author updatedAuthor = Author.builder()
                .id(123)
                .name("Superman")
                .age((short)40)
                .build();

        authorDaoImpl.update(id, updatedAuthor);
        Optional<Author> result = authorDaoImpl.findOne(updatedAuthor.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(updatedAuthor);
    }

    @Test
    @DisplayName("Test author can be deleted")
    void testAuthorCanBeDeleted() {
        Author author = TestDataUtils.testAuthorA();

        authorDaoImpl.create(author);
        authorDaoImpl.delete(author.getId());
        Optional<Author> result = authorDaoImpl.findOne(author.getId());
        assertThat(result).isEmpty();
    }
}
