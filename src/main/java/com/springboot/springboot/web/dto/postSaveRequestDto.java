package com.springboot.springboot.web.dto;

import com.springboot.springboot.domain.post.post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class postSaveRequestDto {
    private String title,content,author;

    @Builder
    public postSaveRequestDto(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public  post toEntity(){
        return post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
