package com.springboot.springboot.service.post;

import com.springboot.springboot.domain.post.post;
import com.springboot.springboot.domain.post.postRepository;
import com.springboot.springboot.web.dto.postListResponseDto;
import com.springboot.springboot.web.dto.postResponseDto;
import com.springboot.springboot.web.dto.postSaveRequestDto;
import com.springboot.springboot.web.dto.postUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<postListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(postListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id){
        post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("no such post. ID="+id));
        postRepository.delete(post);
    }
}
