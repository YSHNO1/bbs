package com.bbs.mapper;

import com.bbs.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
//@Component
@Repository
public interface UserMapper {
    @Insert("insert into user (account_id, name, token, gmt_create, gmt_modified, avatar_url) " +
            "values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
   void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_Id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
