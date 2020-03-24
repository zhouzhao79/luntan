package com.example.luntan.controller;

import com.example.luntan.dto.CommentDTO;
import com.example.luntan.mapper.CommentMapper;
import com.example.luntan.mapper.QuestionExtMapper;
import com.example.luntan.mapper.QuestionMapper;
import com.example.luntan.model.Comment;
import com.example.luntan.model.Question;
import com.example.luntan.model.QuestionExample;
import com.example.luntan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    /**
     * 回复功能
     * @param commentDTO
     * @return
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }

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
        return  new Object();
    }
}
