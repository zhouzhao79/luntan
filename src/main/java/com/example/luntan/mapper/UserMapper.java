package com.example.luntan.mapper;

import com.example.luntan.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user us where us.token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user us where us.id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user us where us.ACCOUNT_ID=#{AccountId}")
    User findByAccountId(@Param("AccountId") String AccountId);

    @Update("UPDATE `user` u set u.GMT_MODIFIED=#{gmtModified},u.TOKEN=#{TOKEN},u.avatar_url=#{avatarUrl} where u.ACCOUNT_ID=#{AccountId}")
    Boolean updateByAccountId(@Param("gmtModified") Long gmtModified,@Param("AccountId") String AccountId,@Param("TOKEN") String TOKEN,@Param("avatarUrl") String avatarUrl);
}
