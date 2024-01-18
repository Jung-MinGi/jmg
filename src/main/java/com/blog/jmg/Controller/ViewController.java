package com.blog.jmg.Controller;

import com.blog.jmg.domain.FindTextParamDTO;
import com.blog.jmg.domain.WriteForm;
import com.blog.jmg.domain.service.TextDataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewController {
    private final TextDataAccessService service;
    @GetMapping("/")
    public String IndexHandler() {
        return "index";
    }

    @GetMapping("/writeShow/{category}/{title}")//작성된 글 보여주는 핸들러
    public String showText(Model model, @ModelAttribute FindTextParamDTO findTextParamDTO){
//            , @PathVariable(value = "category") String category
//            , @PathVariable("title") String title) {
        //contents을 content.html에 보여주기
        log.info("category={} , title={}", findTextParamDTO.toString());
        WriteForm text = service.findText(findTextParamDTO);
        //        WriteForm a = repository.findByCategoryAndTitle(findParam);
//        log.info("a={}",a);
        model.addAttribute("text", text);
        return "view";
    }
}
