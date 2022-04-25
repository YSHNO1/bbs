package com.bbs.mapper;

import com.bbs.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from user where account_Id = accountId")
    User findByAccountId(@Param("accountId") String accountId);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}
