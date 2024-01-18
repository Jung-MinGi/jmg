package com.blog.jmg.domain.service;

import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.repository.mybatis.PagingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagingService {
    private final PagingRepository repository;
    public List<WriteForm> getPaging(Integer pageNum , Integer pageSize, String tableName){
        log.info("getPagingService= {} {} {}",pageNum,pageSize,tableName);
        return repository.getPaging(pageNum,pageSize,tableName);
    }

}
