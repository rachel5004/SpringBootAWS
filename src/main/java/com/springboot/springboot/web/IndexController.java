package com.springboot.springboot.web;

import com.springboot.springboot.config.auth.LoginUser;
import com.springboot.springboot.config.auth.dto.SessionUser;
import com.springboot.springboot.service.post.postService;
import com.springboot.springboot.web.dto.postResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final postService postService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postService.findAllDesc());
        if(user != null) model.addAttribute("userName",user.getName());
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
