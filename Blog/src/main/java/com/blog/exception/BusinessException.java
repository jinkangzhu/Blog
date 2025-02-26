package com.blog.exception;

public class BusinessException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public BusinessException() {
        super();
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
