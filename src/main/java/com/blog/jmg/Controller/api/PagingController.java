package com.blog.jmg.Controller.api;

import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.domain.service.PagingService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paging")
@Slf4j
@RequiredArgsConstructor
public class PagingController {
    private final PagingService service;
    @GetMapping("page")
    public ResponseEntity<PageInfo<WriteForm>> getpage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                              @RequestParam(defaultValue = "5") Integer pageSize,
                                                              @RequestParam String tableName) {
        log.info("pageController= {} {} {}",pageNum,pageSize,tableName);
        PageInfo<WriteForm> p = new PageInfo<>(service.getPaging(pageNum, pageSize,tableName));
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


}
