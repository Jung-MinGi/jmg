package com.blog.jmg.repository.mapper;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataGetMapper {
    List<String> getTablesName();
    WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO);
    void deleteTextByTitle(String category, String title);

}
