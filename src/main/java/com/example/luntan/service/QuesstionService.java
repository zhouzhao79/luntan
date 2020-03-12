package com.example.luntan.service;

import com.example.luntan.dto.PaginationDTO;
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

    public PaginationDTO findAllList(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = quesstionMapper.countAll();
        paginationDTO.setPagination(totalCount,page,size);
        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        int offset=(page-1)*size;
        List<Question> list = quesstionMapper.findAllList(offset,size);
        List<QuestionDTO> questionDTOS=new ArrayList<>();

        for (Question question :list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO findAllList(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = quesstionMapper.countByIdAll(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        int offset=(page-1)*size;
        List<Question> list = quesstionMapper.findByIdAllList(userId,offset,size);
        List<QuestionDTO> questionDTOS=new ArrayList<>();

        for (Question question :list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }
}
