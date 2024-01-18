package com.blog.jmg.domain.s3Domain;

import lombok.Data;

@Data
public class TempImg {
    private final String savedPath;
    private final String originalName;
}