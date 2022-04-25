package com.bbs.service;

import com.bbs.dto.PostDto;
import com.bbs.mapper.PostMapper;
import com.bbs.mapper.UserMapper;
import com.bbs.model.Post;
import com.bbs.model.User;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;
    public List<PostDto> list() {
        List<Post> posts = postMapper.list();
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.findById(post.getCreator());
            PostDto postDto =  new PostDto();
            //spring快速复制对象
            BeanUtils.copyProperties(post, postDto);
            postDto.setUser(user);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }
}
