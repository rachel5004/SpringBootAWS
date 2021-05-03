package com.springboot.springboot.web.dto;

import com.springboot.springboot.domain.post.post;
import lombok.Getter;

@Getter
public class postResponseDto {
    private Long id;
    private String title,content,author;

    public postResponseDto(post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
