package com.blog.jmg.repository.mybatis;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;

import java.util.List;

public interface TextDataGetRepository {
    List<String> getTablesName();
    WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO);
    void deleteTextByTitle(String category,String title);
}
