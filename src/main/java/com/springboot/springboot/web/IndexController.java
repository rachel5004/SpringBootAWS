package com.springboot.springboot.web;

import com.springboot.springboot.service.post.postService;
import com.springboot.springboot.web.dto.postResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final postService postService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }
    @GetMapping("/post/save")
    public String postsave(){
        return "post-save";
    }
    @GetMapping("/post/update/{id}")
    public String postupdate(@PathVariable Long id,Model model){
        postResponseDto dto = postService.findById(id);
        model.addAttribute("post",dto);
        return "post-update";
    }
}
