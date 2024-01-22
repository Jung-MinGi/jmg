package com.blog.jmg.repository.mapper;

import com.blog.jmg.domain.FindTextByIdDTO;
import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataGetMapper {
    void update(WriteForm writeForm);
    void save(WriteForm writeForm);
    List<String> getTablesName();
    WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO);
    WriteForm findTextById(FindTextByIdDTO findTextByIdDTO);
    void deleteTextByTitle(FindTextParamDTO findTextParamDTO);

}
