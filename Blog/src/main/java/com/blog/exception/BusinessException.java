package com.blog.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private BaseMethod exceptionEnum;
    private String errorMessage;

    public BusinessException() {
        super();
    }

    public BusinessException(BaseMethod exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;
    }
    public BusinessException(BaseMethod exceptionEnum, String errorMessage) {
        super(errorMessage);
        this.exceptionEnum = exceptionEnum;
        this.errorMessage = errorMessage;
    }
}