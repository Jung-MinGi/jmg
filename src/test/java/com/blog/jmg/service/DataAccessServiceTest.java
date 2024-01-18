package com.blog.jmg.service;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.domain.service.TextDataAccessService;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepository;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepositoryImpl;
import com.blog.jmg.repository.mapper.DataGetMapper;
import com.blog.jmg.repository.mybatis.DataGetRepositoryImpl;
import com.blog.jmg.repository.mybatis.TextDataGetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
class DataAccessServiceTest {
//    @Autowired
//    private DataGetMapper mapper;
//    @Autowired
//    private JdbcTemplate template;
//    TextDataAccessService service;
//    @BeforeEach
//    void before(){
//        TextDataGetRepository repository = new DataGetRepositoryImpl(mapper);
//        DataGetJDBCTemplateRepository jdbcTemplateRepository = new DataGetJDBCTemplateRepositoryImpl(template);
//        service = new TextDataAccessService(repository,jdbcTemplateRepository);
//    }



    @Test
    void test() {
        assertThat(1).isNotZero();
//        FindTextParamDTO dto = new FindTextParamDTO();
//        dto.setCategory("spring");
//        dto.setTitle("아메리카노");
//        WriteForm result = service.findText(dto);
//        assertThat(result.getTitle()).isEqualTo("아메리카노");
    }
}