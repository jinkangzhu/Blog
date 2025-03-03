package com.blog.exception;

public enum BusinessMsgEnum implements ExceptionAssert{

    // 定义不同的错误类型
    INVALID_USERNAME("401", "User.Login.001", "用户名不能为空"),
    INVALID_PASSWORD("401", "User.Login.002", "密码不能为空:{0}"),
    USER_NOT_FOUND("401", "User.Login.003", "用户未找到"),
    SYSTEM_ERROR("401", "System.001", "系统异常:{0}"),
    USER_DUPLICATE("500","User.Register.001","用户名重复！");


    private String statusCode;
    private String errorCode;
    private String message;


    // 构造方法
    BusinessMsgEnum(String statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
