package com.blog.jmg.service;

import com.blog.jmg.domain.service.TextDataAccessService;
import com.blog.jmg.repository.mapper.DataGetMapper;
import com.blog.jmg.repository.mybatis.DataGetRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class DataAccessServiceTest {
    @Autowired
    private DataGetMapper mapper;

    @Test
    void test() {
        TextDataAccessService service = new TextDataAccessService(new DataGetRepositoryImpl(mapper));
        List<String> tables = service.getTables();
        assertThat(tables.size()).isNotZero();
    }
}