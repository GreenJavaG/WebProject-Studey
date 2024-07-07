package com.gq.exception;


import com.gq.uitl.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 全局异常处理器
* */
@RestControllerAdvice
public class ClobalExceptionHandler {
    //当出现异常时，捕捉异常，并返回Result风格数据
    @ExceptionHandler(Exception.class)
    public Result ex(Exception exception){
        exception.printStackTrace();
        return Result.err("对不起，操作失败，请联系管理员");
    }
}
