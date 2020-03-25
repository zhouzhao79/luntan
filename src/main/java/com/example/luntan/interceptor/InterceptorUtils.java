package com.example.luntan.interceptor;

import com.example.luntan.mapper.UserMapper;
import com.example.luntan.model.User;
import com.example.luntan.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Component
public class InterceptorUtils {
    @Autowired
    private static UserMapper userMapper;

    public static List<User> qingqiu(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("suijiID", UUID.randomUUID().toString()));
        Cookie[] cookies = request.getCookies();
        //  System.out.println("我进来了");
        List<User> users = null;
        if (cookies != null) {
            for (Cookie cookie : cookies
            ) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    users = userMapper.selectByExample(userExample);
                    //    User user = userMapper.findByToken(token);
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    break;
                }

            }

        }
        return users;
    }

}