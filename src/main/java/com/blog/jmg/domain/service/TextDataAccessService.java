package com.blog.jmg.domain.service;

import com.blog.jmg.domain.FindTextByIdDTO;
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

    public void update(WriteForm writeForm){
        repository.update(writeForm);
    }
    public void save(WriteForm writeForm) {
        repository.save(writeForm);
    }

    public List<String> getTables() {
        return repository.getTablesName();
    }

    public WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO) {
        return repository.findTextByTitle(findTextParamDTO);
    }
    public WriteForm findTextById(FindTextByIdDTO findTextByIdDTO) {
        return repository.findTextById(findTextByIdDTO);
    }

    public void deleteText(FindTextParamDTO findTextParamDTO) {
        repository.deleteTextByTitle(findTextParamDTO);
        jdbcRepository.autoIncrementReset(findTextParamDTO.getCategory());
    }
}
