package com.blog.jmg.repository.mapper;

import com.blog.jmg.domain.WriteForm;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PagingMapper {
    Page<WriteForm> getPaging(String tableName);
}
