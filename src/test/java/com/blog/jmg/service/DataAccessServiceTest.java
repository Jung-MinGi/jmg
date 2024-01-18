package com.blog.jmg.service;

import com.blog.jmg.domain.service.TextDataAccessService;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepositoryImpl;
import com.blog.jmg.repository.mapper.DataGetMapper;
import com.blog.jmg.repository.mybatis.DataGetRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class DataAccessServiceTest {
    @Autowired
    private DataGetMapper mapper;
    @Autowired
    private DataSource dataSource;

    @Test
    void test() {
        TextDataAccessService service = new TextDataAccessService(new DataGetRepositoryImpl(mapper)
                , new DataGetJDBCTemplateRepositoryImpl(new JdbcTemplate(dataSource)));
        List<String> tables = service.getTables();
        assertThat(tables.size()).isNotZero();
    }
}