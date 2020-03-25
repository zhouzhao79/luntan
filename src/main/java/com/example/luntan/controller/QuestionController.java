package com.example.luntan.controller;

import com.example.luntan.util.RedisUtils;
import com.example.luntan.externmy.PageView;
import com.example.luntan.dto.QuestionDTO;
import com.example.luntan.model.User;
import com.example.luntan.service.QuesstionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    private QuesstionService quesstionService;
    @Autowired
    private RedisUtils redisUtil;

    @PageView
    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model, HttpServletRequest request){
//        User user=(User) request.getSession().getAttribute("user");
//        if (user==null){
//           return "redirect:/";
//        }
        QuestionDTO questionDTO=quesstionService.findByQuestionId(id);
        //累加阅读数
       // quesstionService.incView(id);
      //  System.out.println(IpUtils.getIpAddr(request));
        String key = "articleId_"+id;
        Long view = redisUtil.size(key);
      //  System.out.println(view);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
