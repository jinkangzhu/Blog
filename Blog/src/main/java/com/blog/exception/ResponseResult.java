package com.blog.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {
    private String statusCode;
    private String errorCode;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.statusCode = "200";
        responseResult.message = "success";
        return responseResult;
    }

    public static <T> ResponseResult<T> success(String message) {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.statusCode = "200";
        responseResult.message = message;
        return responseResult;
    }
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.statusCode = "200";
        responseResult.message = "success";
        responseResult.data = data;
        return responseResult;
    }

    public static <T> ResponseResult<T> success(String message,T data) {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.data = data;
        responseResult.statusCode = "200";
        responseResult.message = message;
        return responseResult;
    }

    public static <T> ResponseResult<T> error(String statusCode,String errorCode,String message) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.statusCode = statusCode;
        responseResult.errorCode = errorCode;
        responseResult.message = message;
        return responseResult;
    }
}
