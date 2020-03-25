package com.example.luntan.interceptor;

import com.example.luntan.mapper.UserMapper;
import com.example.luntan.model.User;
import com.example.luntan.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
public class IndexSessionInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<User> users = InterceptorUtils.qingqiu(request, response);
//        response.addCookie(new Cookie("suijiID", UUID.randomUUID().toString()));
//        Cookie[] cookies = request.getCookies();
//      //  System.out.println("我进来了");
//        List<User> users=null;
//        if (cookies!=null) {
//            for (Cookie cookie : cookies
//            ) {
//                if (cookie.getName().equals("token")) {
//                    String token = cookie.getValue();
//                    UserExample userExample = new UserExample();
//                    userExample.createCriteria().andTokenEqualTo(token);
//                    users = userMapper.selectByExample(userExample);
//                //    User user = userMapper.findByToken(token);
//                    if (users.size() != 0) {
//                        request.getSession().setAttribute("user", users.get(0));
//                    }
//                    break;
//                }
//
//            }
//
//        }

            return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
