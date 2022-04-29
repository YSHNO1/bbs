package com.bbs.service;

import com.bbs.dto.PageDto;
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
    public PageDto list(Integer page, Integer size) {
        PageDto  pageDto = new PageDto();
        Integer totalCount = postMapper.count();  //记录总数
        pageDto.setPage(totalCount, page, size);
        if(page < 1) page = 1;
        if(page > pageDto.getTotalPage()) page = pageDto.getTotalPage();
        Integer offset = size * (page - 1);
        if(totalCount == 0) offset = 0;
        List<Post> posts = postMapper.list(offset, size);
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.findById(post.getCreator());
            PostDto postDto = new PostDto();
            //spring快速复制对象
            BeanUtils.copyProperties(post, postDto);
            postDto.setUser(user);
            postDtoList.add(postDto);
        }
        pageDto.setPosts(postDtoList);
        return pageDto;
    }

    public PageDto list(Integer id, Integer page, Integer size) {
        PageDto  pageDto = new PageDto();
        Integer totalCount = postMapper.countByUserId(id);  //记录总数
        pageDto.setPage(totalCount, page, size);
        if(page < 1) page = 1;
        if(page > pageDto.getTotalPage()) page = pageDto.getTotalPage();
        Integer offset = size * (page - 1);
        if(totalCount == 0) offset = 0;
        List<Post> posts = postMapper.listByUserId(id, offset, size);
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.findById(post.getCreator());
            PostDto postDto =  new PostDto();
            //spring快速复制对象
            BeanUtils.copyProperties(post, postDto);
            postDto.setUser(user);
            postDtoList.add(postDto);
        }
        pageDto.setPosts(postDtoList);
        return pageDto;
    }

    public PostDto getById(Integer id) {
        Post post =  postMapper.getById(id);
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
        User user = userMapper.findById(post.getCreator());
        postDto.setUser(user);
        return postDto;
    }
}
