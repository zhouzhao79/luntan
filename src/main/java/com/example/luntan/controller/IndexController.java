package com.example.luntan.controller;

import com.example.luntan.mapper.UserMapper;
import com.example.luntan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;//这样是有问题的，需要service层

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response){
        String cookie1=request.getHeader("cookie");
        response.addCookie(new Cookie("suijiID", UUID.randomUUID().toString()));
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies
            ) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }

            }
       }
        return "index";
    }


}
