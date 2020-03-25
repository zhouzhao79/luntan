package com.example.luntan.service.impl;

import com.example.luntan.dto.CommentDTO;
import com.example.luntan.mapper.CommentMapper;
import com.example.luntan.mapper.QuestionExtMapper;
import com.example.luntan.model.Comment;
import com.example.luntan.model.Question;
import com.example.luntan.model.User;
import com.example.luntan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    public int insert(CommentDTO commentDTO, User user) {
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setCommentText(commentDTO.getCommentText());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDTO.getType());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        int i = commentMapper.insert(comment);
        if (i==1){
            //添加题目的回复数量
            Question question=new Question();
            question.setId(commentDTO.getParentId().intValue());
            question.setCommentCount(1);
            questionExtMapper.inCommentCount(question);
        }
        return 0;
    }
}
