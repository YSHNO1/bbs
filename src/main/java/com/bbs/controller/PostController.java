package com.bbs.controller;

import com.bbs.dto.PostDto;
import com.bbs.mapper.PostMapper;
import com.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") Integer id,
                       Model model){
        PostDto postDto = postService.getById(id);
        model.addAttribute("post", postDto);
        return "post";
    }
}
