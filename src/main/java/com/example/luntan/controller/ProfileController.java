package com.example.luntan.controller;

import com.example.luntan.dto.PaginationDTO;
import com.example.luntan.model.User;
import com.example.luntan.service.QuesstionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProfileController {
    @Autowired
    private QuesstionService quesstionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "9")Integer size){
        User user=(User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        }else if ("repies".equals(action)){
            model.addAttribute("section", "repies");
            model.addAttribute("sectionName", "最新回复");
        }
        PaginationDTO allList = quesstionService.findAllList(user.getId(), page, size);
        model.addAttribute("pagination",allList);
        return "profile";
   }
}
