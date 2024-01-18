package com.blog.jmg.repository.mybatis;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.repository.mapper.DataGetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DataGetRepositoryImpl implements TextDataGetRepository {
    private final DataGetMapper mapper;

    @Override
    public List<String> getTablesName() {
        return mapper.getTablesName();
    }

    @Override
    public WriteForm findTextByTitle(FindTextParamDTO findTextParamDTO) {
        return mapper.findTextByTitle(findTextParamDTO);
    }

    @Override
    public void deleteTextByTitle(@Param("category") String category,@Param("title") String title) {
        mapper.deleteTextByTitle(category, title);
    }

}