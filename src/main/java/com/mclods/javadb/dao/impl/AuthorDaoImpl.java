package com.mclods.javadb.dao.impl;

import com.mclods.javadb.dao.AuthorDao;
import com.mclods.javadb.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO author(name, age) VALUES(?, ?)",
                author.getName(),
                author.getAge());
    }

    @Override
    public List<Author> findOne(Integer id) {
        return jdbcTemplate.query("SELECT id, name, age FROM author WHERE id = ? LIMIT 1",
                new AuthorRowMapper(),
                id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .age(rs.getShort("age"))
                    .build();
        }
    }
}
