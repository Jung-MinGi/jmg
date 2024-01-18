package com.blog.jmg.repository.mybatis;

import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.repository.mapper.PagingMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PagingRepository {

     List<WriteForm> getPaging(Integer pageNum , Integer pageSize, String tableName);

}
