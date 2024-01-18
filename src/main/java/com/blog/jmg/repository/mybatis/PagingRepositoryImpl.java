package com.blog.jmg.repository.mybatis;

import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.repository.mapper.PagingMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PagingRepositoryImpl implements PagingRepository {
    private final PagingMapper mapper;

    public List<WriteForm> getPaging(Integer pageNum, Integer pageSize, String tableName) {
        log.info("getPagingrepo= {} {} {}", pageNum, pageSize, tableName);
        PageHelper.startPage(pageNum, pageSize);
        return mapper.getPaging(tableName);
    }
}
