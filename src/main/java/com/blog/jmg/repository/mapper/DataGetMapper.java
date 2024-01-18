package com.blog.jmg.repository.mapper;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataGetMapper {
    List<String> getTablesName();
    WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO);
    void deleteTextByTitle(String category, String title);

}
