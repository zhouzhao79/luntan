package com.example.luntan.service;

import com.example.luntan.dto.PaginationDTO;
import com.example.luntan.dto.QuestionDTO;
import com.example.luntan.mapper.QuesstionMapper;
import com.example.luntan.mapper.QuestionMapper;
import com.example.luntan.mapper.UserMapper;
import com.example.luntan.model.Question;
import com.example.luntan.model.QuestionExample;
import com.example.luntan.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuesstionService {

    @Autowired
    private QuestionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO findAllList(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        int totalCount = (int) quesstionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagination(totalCount,page,size);
        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        int offset=(page-1)*size;
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> list = quesstionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS=new ArrayList<>();

        for (Question question :list) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            System.out.println(questionDTO);
            System.out.println(question);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO findAllList(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        int totalCount = (int)quesstionMapper.countByExample(questionExample);
        paginationDTO.setPagination(totalCount,page,size);
        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        int offset=(page-1)*size;

        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria().andCreatorEqualTo(userId);
        List<Question> list = quesstionMapper.selectByExampleWithRowbounds(questionExample1, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS=new ArrayList<>();

        for (Question question :list) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO findByQuestionId(Integer id) {
        Question question=quesstionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO=new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            quesstionMapper.insert(question);
        }else {
            //更新
            question.setGmtModified(question.getGmtCreate());
//            QuestionExample example = new QuestionExample();
//            example.createCriteria()
//                    .andIdEqualTo(question.getId());
//            quesstionMapper.updateByExampleSelective(question,example);
            quesstionMapper.updateByPrimaryKeySelective(question);
        }
    }
}
