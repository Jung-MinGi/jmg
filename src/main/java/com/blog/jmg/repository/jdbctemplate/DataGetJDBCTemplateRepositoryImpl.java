package com.blog.jmg.repository.jdbctemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DataGetJDBCTemplateRepositoryImpl implements DataGetJDBCTemplateRepository {
    private final JdbcTemplate template;

    @Override
    public void autoIncrementReset(String category) {
        String sql = "ALTER TABLE " + category + " AUTO_INCREMENT=1";
        template.update(sql);
        sql = "set @count=0";
        template.update(sql);
        sql = "update " + category + " set id = @count:=@count+1;";//여기서 걸면 안될까
        template.update(sql);
    }
}
