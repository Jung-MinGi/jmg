package com.blog.jmg.repository.mybatis;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.domain.service.TextDataAccessService;
import com.blog.jmg.repository.mapper.DataGetMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MybatisTest
@ActiveProfiles("test")
class DataGetRepositoryImplTest {
    @Autowired
    private DataGetMapper mapper;
    DataGetRepositoryImpl repository;

    @BeforeEach
    void before(){
        repository = new DataGetRepositoryImpl(mapper);
    }

    @Test
    void test(){
        WriteForm writeForm = new WriteForm();
        writeForm.setCategory("spring");
        writeForm.setTitle("title1");
        writeForm.setContent("hello");
        repository.save(writeForm);
        FindTextParamDTO dto = new FindTextParamDTO();
        dto.setCategory(writeForm.getCategory());
        dto.setTitle(writeForm.getTitle());
        WriteForm result = repository.findTextByTitle(dto);
        assertThat(result.getTitle()).isEqualTo(writeForm.getTitle());

        //update test
        writeForm.setTitle("title수정");
        repository.update(writeForm);
        WriteForm w = repository.findTextByTitle(dto);
        assertThat(w.getTitle()).isNotEqualTo(writeForm.getTitle());
    }
    @Test
    void test_occur_EX(){
        WriteForm writeForm = new WriteForm();
        writeForm.setCategory("EX");
        writeForm.setTitle("title1");
        writeForm.setContent("hello");
        assertThatThrownBy(()->repository.save(writeForm))
                .isInstanceOf(DataAccessException.class);
    }

}