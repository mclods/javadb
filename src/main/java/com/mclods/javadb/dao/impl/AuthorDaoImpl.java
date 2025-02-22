package com.mclods.javadb.dao.impl;

import com.mclods.javadb.dao.AuthorDao;
import com.mclods.javadb.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO author(name, age) VALUES(?, ?)", author.getName(), author.getAge());
    }
}
