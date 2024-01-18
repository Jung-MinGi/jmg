package com.blog.jmg.Controller.api;

import com.blog.jmg.domain.service.TextDataAccessService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TableDataGetApiController {
    private final TextDataAccessService service;

    @GetMapping("/tables")
    public ResponseEntity<List<String>> getAllTablesName() {
        List<String> tables = service.getTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }
    @PostMapping("/delete/{category}/{title}")
    public ResponseEntity<String> deleteText(@PathVariable String category
    ,@PathVariable String title) throws IOException {
        log.info("deleteText={} {}",category,title);
//        Map<String, String> map = objectMapper.readValue(id, new TypeReference<>() {
//        });
        service.deleteText(category,title);
        return new ResponseEntity<>("글 삭제 완료", HttpStatus.OK);
    }


}
