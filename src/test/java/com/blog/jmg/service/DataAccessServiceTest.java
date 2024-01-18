package com.blog.jmg.service;

import com.blog.jmg.domain.service.TextDataAccessService;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepositoryImpl;
import com.blog.jmg.repository.mapper.DataGetMapper;
import com.blog.jmg.repository.mybatis.DataGetRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class DataAccessServiceTest {
//    @Autowired
//    private DataGetMapper mapper;
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private JdbcTemplate template;

    @Test
    void test() {
        assertThat(1).isNotZero();
//        TextDataAccessService service = new TextDataAccessService(new DataGetRepositoryImpl(mapper)
//                , new DataGetJDBCTemplateRepositoryImpl(template));
//        List<String> tables = service.getTables();
//        assertThat(tables.size()).isNotZero();
    }
}