package com.springboot.springboot.web.dto;

import com.springboot.springboot.domain.post.post;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class postListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public postListResponseDto(post entity){
       this.id = entity.getId();
       this.title = entity.getTitle();
       this.author = entity.getAuthor();
       this.modifiedDate = entity.getModifiedDate();
    }
}
