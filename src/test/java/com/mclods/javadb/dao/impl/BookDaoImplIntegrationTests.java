package com.mclods.javadb.dao.impl;

import com.mclods.javadb.TestDataUtils;
import com.mclods.javadb.dao.AuthorDao;
import com.mclods.javadb.domain.Author;
import com.mclods.javadb.domain.Book;
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
        Author author = TestDataUtils.testAuthorA();
        Book book = TestDataUtils.testBookA();

        authorDao.create(author);
        bookDaoImpl.create(book);
        Optional<Book> result =  bookDaoImpl.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    @DisplayName("Test multiple books can be created and recalled")
    void testMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.testAuthorA();

        Book bookA = TestDataUtils.testBookA();
        Book bookB = TestDataUtils.testBookB();
        Book bookC = TestDataUtils.testBookC();

        bookA.setAuthorId(1);
        bookB.setAuthorId(1);
        bookC.setAuthorId(1);

        authorDao.create(author);
        bookDaoImpl.create(bookA);
        bookDaoImpl.create(bookB);
        bookDaoImpl.create(bookC);
        List<Book> result = bookDaoImpl.find();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(bookA, bookB, bookC);
    }

    @Test
    @DisplayName("Test book can be updated")
    void testBookCanBeUpdated() {
        Author authorA = TestDataUtils.testAuthorA();
        Author authorB = TestDataUtils.testAuthorB();

        Book book = TestDataUtils.testBookA();
        book.setAuthorId(authorA.getId());
        String isbn = book.getIsbn();

        authorDao.create(authorA);
        bookDaoImpl.create(book);

        // Updated book
        Book updatedBook = Book.builder()
                .isbn("random_isbn_123")
                .title("test book")
                .authorId(authorB.getId())
                .build();

        authorDao.create(authorB);
        bookDaoImpl.update(isbn, updatedBook);
        Optional<Book> result = bookDaoImpl.findOne(updatedBook.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(updatedBook);
    }

    @Test
    @DisplayName("Test book can be deleted")
    void testBookCanBeDeleted() {
        Author author = TestDataUtils.testAuthorA();
        Book book = TestDataUtils.testBookA();

        authorDao.create(author);
        bookDaoImpl.create(book);

        bookDaoImpl.delete(book.getIsbn());
        Optional<Book> result = bookDaoImpl.findOne(book.getIsbn());
        assertThat(result).isEmpty();
    }
}
