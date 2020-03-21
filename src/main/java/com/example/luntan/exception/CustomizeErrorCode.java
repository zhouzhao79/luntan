package com.example.luntan.exception;

/**
 * 这样做能定义多种不同类型的异常枚举
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESESTION_NOT_FOUND("你找到问题不在了，要不要换个试试？");

    private String message;



    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
