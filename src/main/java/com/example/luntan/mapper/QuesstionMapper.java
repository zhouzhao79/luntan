package com.example.luntan.mapper;

import com.example.luntan.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuesstionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) VALUES (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from  question order by gmt_create desc limit #{offset},#{size}")
    List<Question> findAllList(@Param("offset")int offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    Integer countAll();

    @Select("select * from  question where creator=#{userId} order by gmt_create desc limit #{offset},#{size}")
    List<Question> findByIdAllList(@Param("userId")Integer userId, @Param("offset")int offset,@Param("size") Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByIdAll(@Param("userId")Integer userId);

    @Select("select * from question where id=#{id}")
    Question findByQuestionId(@Param("id")Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void updateById(Question question);
}
