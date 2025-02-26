package com.blog.exception;

public class BusinessException extends RuntimeException {
    private static final String BUSINESS_EXCEPTION_MESSAGE =
            "CustomException";

    private String errorCode;
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String errorCode,String message, Throwable t) {
        super(message, t);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
