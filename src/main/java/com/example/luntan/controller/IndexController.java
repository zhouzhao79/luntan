package com.example.luntan.controller;

import com.example.luntan.Aspect.RedisUtils;
import com.example.luntan.dto.PaginationDTO;
import com.example.luntan.service.QuesstionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @Autowired
    private QuesstionService quesstionService;
    @Autowired
    private RedisUtils redisUtil;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "9")Integer size
                        ){
        PaginationDTO pagination = quesstionService.findAllList(page, size);
        model.addAttribute("pagination",pagination);
        return "index";
    }


}
