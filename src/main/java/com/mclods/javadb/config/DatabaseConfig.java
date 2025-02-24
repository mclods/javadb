package com.mclods.javadb.config;

import com.mclods.javadb.dao.AuthorDao;
import com.mclods.javadb.dao.BookDao;
import com.mclods.javadb.dao.impl.AuthorDaoImpl;
import com.mclods.javadb.dao.impl.BookDaoImpl;
import com.mclods.javadb.domain.Author;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AuthorDao authorDao(JdbcTemplate jdbcTemplate) {
        return new AuthorDaoImpl(jdbcTemplate);
    }

    @Bean
    public BookDao bookDao(JdbcTemplate jdbcTemplate) {
        return new BookDaoImpl(jdbcTemplate);
    }
}
