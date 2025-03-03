package com.blog.handler;

import com.blog.exception.BaseMethod;
import com.blog.exception.BusinessException;
import com.blog.exception.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseResult exceptionHandler(BusinessException e){
        BaseMethod exceptionEnum = e.getExceptionEnum();
        log.error("异常信息:{}",e);
        return ResponseResult.error(exceptionEnum.getStatusCode(),exceptionEnum.getErrorCode(), e.getMessage());
    }

    /**
     * 处理所有的运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseResult exceptionHandler(RuntimeException e) {
        log.error("系统异常：{}", e);
        return ResponseResult.error("500", "RuntimeException", e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseResult exceptionHandler(SQLIntegrityConstraintViolationException ex){
//        String message = ex.getMessage();
//        if (message.contains("Duplicate entry")){
//            String[] split = message.split(" ");
//            String username = split[2];
//            String msg = split[2] + MessageConstant.ALREADY_EXITS;
//            return Result.error(msg);
//        }else {
//            return Result.error(MessageConstant.UNKNOWN_ERROR);
//        }
        return ResponseResult.error("500", "数据库异常", ex.getMessage());
    }
}
