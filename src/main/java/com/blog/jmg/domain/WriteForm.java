package com.blog.jmg.domain;

import lombok.Data;

@Data
public class WriteForm {
    private int id;
    private String category;
    private String title;
    private String content;
}
