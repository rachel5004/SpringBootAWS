package com.springboot.springboot.web;

import com.springboot.springboot.service.post.postService;
import com.springboot.springboot.web.dto.postResponseDto;
import com.springboot.springboot.web.dto.postSaveRequestDto;
import com.springboot.springboot.web.dto.postUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class postApiController {
    private final postService postService;

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody postSaveRequestDto requestDto){
        return postService.save(requestDto);
    }
    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id,@RequestBody postUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }
    @GetMapping("/api/v1/post/{id}")
    public postResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }
    @DeleteMapping("/api/v1/post/{id}")
    public Long delete(@PathVariable Long id){
        postService.delete(id);
        return id;
    }
}
