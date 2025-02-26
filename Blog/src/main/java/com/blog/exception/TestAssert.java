package com.blog.exception;

public class TestAssert implements CustomAssert{

    private String errorCode;
    private String message;
    private String statusCode;


    @Override
    public BusinessException newException() {
        return new BusinessException();
    }

    @Override
    public BusinessException newException(Throwable t) {
        return new BusinessException(t);
    }
}
