package com.example.luntan.mapper;

import com.example.luntan.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
}
