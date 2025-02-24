package com.mclods.javadb.dao.impl;

import com.mclods.javadb.dao.BookDao;
import com.mclods.javadb.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, isbn, author_id) VALUES(?, ?, ?)",
                book.getTitle(),
                book.getIsbn(),
                book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> result = jdbcTemplate.query("SELECT title, isbn, author_id FROM book WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn);
        return result.stream().findFirst();
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .title(rs.getString("title"))
                    .isbn(rs.getString("isbn"))
                    .authorId(rs.getInt("author_id"))
                    .build();
        }
    }
}
