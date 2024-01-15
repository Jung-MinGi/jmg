package com.blog.jmg.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TmpController {
    @GetMapping("/")
    public String root() {
        return "index";
    }
}
