package com.example.luntan.service;

import com.example.luntan.dto.QuestionDTO;
import com.example.luntan.mapper.QuesstionMapper;
import com.example.luntan.mapper.UserMapper;
import com.example.luntan.model.Question;
import com.example.luntan.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuesstionService {

    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> findAllList() {
        List<Question> list = quesstionMapper.findAllList();
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        for (Question question :list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
