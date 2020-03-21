package com.example.luntan.Aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Configuration
@Slf4j
public class PageViewAspect {

    @Autowired
    private RedisUtils redisUtil;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.luntan.externmy.PageView)")
    public void PageViewAspect() {

    }

    @Autowired
    HttpServletRequest request;

    /**
     * 切入处理
     * @param joinPoint
     * @return
     */
    @Around("PageViewAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        Object[] object = joinPoint.getArgs();
        Object articleId = object[0];
        log.info("articleId:{}", articleId);
        Object obj = null;
        try {
         //   String ipAddr = IpUtils.getIpAddr();
            String ip=IpUtils.getIpAddr(request);
            System.out.println(ip);
            log.info("ipAddr:{}", ip);
            String key = "articleId_" + articleId;
        //    System.out.println(key);
            // 浏览量存入redis中
            Long num = redisUtil.add(key,ip);
            if (num == 0) {
                log.info("该ip:{},访问的浏览量已经新增过了", ip);
            }
            obj = joinPoint.proceed();
         //   System.out.println("我运行了"+obj);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
