package com.blog.jmg.service;

import com.blog.jmg.domain.FindTextByIdDTO;
import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.domain.service.TextDataAccessService;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepository;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepositoryImpl;
import com.blog.jmg.repository.mapper.DataGetMapper;
import com.blog.jmg.repository.mybatis.DataGetRepositoryImpl;
import com.blog.jmg.repository.mybatis.TextDataGetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DataAccessServiceTest {
    @Autowired
    private DataGetMapper mapper;
    @Autowired
    private JdbcTemplate template;
    TextDataAccessService service;
    @BeforeEach
    void before(){
        TextDataGetRepository repository = new DataGetRepositoryImpl(mapper);
        DataGetJDBCTemplateRepository jdbcTemplateRepository = new DataGetJDBCTemplateRepositoryImpl(template);
        service = new TextDataAccessService(repository,jdbcTemplateRepository);
    }



    @Test
    void test() {
//        assertThat(1).isNotZero();
        FindTextByIdDTO dto = new FindTextByIdDTO();
        dto.setCategory("spring");
        dto.setId("1");
        WriteForm result = service.findTextById(dto);
        assertThat(result.getTitle()).isEqualTo("아메리카노");

        List<String> tables = service.getTables();
        assertThat(tables.size()).isEqualTo(1);
    }

}