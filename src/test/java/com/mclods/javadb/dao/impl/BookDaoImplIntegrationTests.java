package com.mclods.javadb.dao.impl;

import com.mclods.javadb.TestDataUtils;
import com.mclods.javadb.dao.AuthorDao;
import com.mclods.javadb.domain.Author;
import com.mclods.javadb.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BookDaoImplIntegrationTests {
    private final AuthorDao authorDao;
    private final BookDaoImpl bookDaoImpl;

    @Autowired
    public BookDaoImplIntegrationTests(AuthorDao authorDao, BookDaoImpl bookDaoImpl) {
        this.authorDao = authorDao;
        this.bookDaoImpl = bookDaoImpl;
    }

    @Test()
    @DisplayName("Test book can be created and recalled")
    void testBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.testAuthor();
        Book book = TestDataUtils.testBook();

        authorDao.create(author);
        bookDaoImpl.create(book);
        Optional<Book> result =  bookDaoImpl.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
