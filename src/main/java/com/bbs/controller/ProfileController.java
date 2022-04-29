package com.bbs.controller;

import com.bbs.dto.PageDto;
import com.bbs.model.User;
import com.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    PostService postService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size){

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) return "redirect:/";
        if("posts".equals(action)){
            model.addAttribute("section", "posts");
            model.addAttribute("sectionName", "我的发帖");
        } else if("replies".equals(action)){
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PageDto pagination = postService.list(user.getId(), page, size);
        model.addAttribute("pagination", pagination);
        return "profile";
    }
}
