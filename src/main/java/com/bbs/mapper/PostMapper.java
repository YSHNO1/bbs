package com.bbs.mapper;

import com.bbs.dto.PostDto;
import com.bbs.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("insert into post (title, description, gmt_create, gmt_modified, creator, tag)" +
            "values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag});")
    public void create(Post post);

    //拿到所有的post
    @Select("select * from post limit #{offset},#{size}")
    List<Post> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);

    //拿到post的总数
    @Select("select count(1) from post")
    Integer count();

    //获取单个用户的发帖
    @Select("select * from post where creator = #{userId} limit #{offset},#{size}")
    List<Post> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size")Integer size);

    //查询单个用户的发帖总数
    @Select("select count(1) from post where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    //获取具体的帖子内容
    @Select("select * from post where id = #{id}")
    Post getById(@PathVariable(value = "id") Integer id);
}
