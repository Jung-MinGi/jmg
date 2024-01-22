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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewController {
    private final TextDataAccessService service;
    @GetMapping("/")
    public String IndexHandler() {
        return "index";
    }

    @GetMapping("/image")//글 작성 페이지로 이동 핸들러
    public String summer(Model model) {
        List<String> list = service.getTables();
        log.info("show tables={}", list);
        model.addAttribute("tables", list);
        return "board";
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

    @GetMapping("/update/{category}/{title}")//글 수정페이지로 이동하는 핸들러
    public String updateForm(@PathVariable("title") String title
            ,@PathVariable("category") String category,
                             Model model) {
        List<String> list = service.getTables();
        FindTextParamDTO dto = new FindTextParamDTO();
        dto.setCategory(category);
        dto.setTitle(title);
        WriteForm text = service.findText(dto);
        model.addAttribute("tables", list);
        model.addAttribute("contents", text);
        return "update";
    }
}
