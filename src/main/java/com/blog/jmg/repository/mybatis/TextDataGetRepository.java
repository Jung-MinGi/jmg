package com.blog.jmg.repository.mybatis;

import com.blog.jmg.domain.FindTextByIdDTO;
import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;

import java.util.List;

public interface TextDataGetRepository {
    void update(WriteForm writeForm);
    void save(WriteForm writeForm);
    List<String> getTablesName();
    WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO);
    WriteForm findTextById(FindTextByIdDTO findTextByIdDTO);
    void deleteTextByTitle(FindTextParamDTO findTextParamDTO);
}
