package com.springboot.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class postUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public postUpdateRequestDto(String title,String content){
        this.title = title;
        this.content = content;
    }
}
