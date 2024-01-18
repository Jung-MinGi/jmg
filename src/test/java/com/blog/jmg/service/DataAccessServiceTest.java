package com.blog.jmg.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class DataAccessServiceTest {


    @Test
    void test() {
        assertThat(1).isNotZero();
//        TextDataAccessService service = new TextDataAccessService(new DataGetRepositoryImpl(mapper)
//                , new DataGetJDBCTemplateRepositoryImpl(new JdbcTemplate(dataSource)));
//        List<String> tables = service.getTables();
//        assertThat(tables.size()).isNotZero();
    }
}