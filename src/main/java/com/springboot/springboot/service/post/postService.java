package com.springboot.springboot.service.post;

import com.springboot.springboot.domain.post.post;
import com.springboot.springboot.domain.post.postRepository;
import com.springboot.springboot.web.dto.postResponseDto;
import com.springboot.springboot.web.dto.postSaveRequestDto;
import com.springboot.springboot.web.dto.postUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class postService {
    private final postRepository postRepository;

    @Transactional
    public Long save(postSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, postUpdateRequestDto requestDto){
        post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("no such post. ID="+id));
        post.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }
    public postResponseDto findById(Long id){
        post entity = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("no such post. ID="+id));
        return new postResponseDto(entity);
    }
}
