package com.bbs.mapper;

import com.bbs.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("insert into post (title, description, gmt_create, gmt_modified, creator, tag)" +
            "values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag});")
    public void create(Post post);

    //拿到所有的post
    @Select("select * from post")
    List<Post> list();
}
