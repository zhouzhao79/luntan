package com.example.luntan.controller;

import com.example.luntan.dto.CommentDTO;
import com.example.luntan.dto.ResultDTO;
import com.example.luntan.mapper.CommentMapper;
import com.example.luntan.mapper.QuestionExtMapper;
import com.example.luntan.mapper.QuestionMapper;
import com.example.luntan.model.Comment;
import com.example.luntan.model.Question;
import com.example.luntan.model.QuestionExample;
import com.example.luntan.model.User;
import com.example.luntan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 回复功能
     * @param commentDTO
     * @return
     */
    @RequestMapping(value = "/yw/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }

        commentService.insert(commentDTO,user);

        return  ResultDTO.okOf();
    }
}
