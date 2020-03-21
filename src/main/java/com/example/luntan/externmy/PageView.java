package com.example.luntan.externmy;

import java.lang.annotation.*;

/**
 * 自定义注解,用于新增文章浏览量到redis中
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageView {
    /**
     * 描述
     */
    String description()  default "";

    /**
     * @Retention(RetentionPolicy.SOURCE)
     * @Target(ElementType.TYPE)
     */
}
