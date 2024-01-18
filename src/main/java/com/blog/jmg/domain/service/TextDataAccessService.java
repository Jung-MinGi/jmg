package com.blog.jmg.domain.service;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.repository.jdbctemplate.DataGetJDBCTemplateRepository;
import com.blog.jmg.repository.mybatis.DataGetRepositoryImpl;
import com.blog.jmg.repository.mybatis.TextDataGetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TextDataAccessService {
private final TextDataGetRepository repository;
private final DataGetJDBCTemplateRepository jdbcRepository;
    public List<String> getTables(){
        return repository.getTablesName();
    }
    public WriteForm findText(FindTextParamDTO findTextParamDTO){
        return repository.findTextByTitle(findTextParamDTO);
    }
    @Transactional
    public void deleteText(String category,String title){
        repository.deleteTextByTitle(category,title);
        jdbcRepository.autoIncrementReset(category);
    }
}
