package cn.edu.njupt.forum.exception;


import cn.edu.njupt.forum.enums.ErrorEnum;
import cn.edu.njupt.forum.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("all")
public class GlobalExceptionHandler {

    @ExceptionHandler(LocalRuntimeException.class)
    public Result localRunTimeException(LocalRuntimeException e) {
        //LocalRuntimeException本意其实是给用户看的错误信息，没有必要打印全栈帧
        //这里打出第一个栈帧的用处其实是为了用于告诉开发者哪一行出错了，不然没有栈帧，对于一些逻辑错误可能会比较难调试
        log.error("""
                error msg: {}
                first stackTrace: {}
                """, e.getMessage(), e.getStackTrace()[0]);
        if (e.getError() != null) {
            return Result.failure(e.getError(), e.getMessage());
        } else {
            return Result.failure(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerValidationException(MethodArgumentNotValidException e) {
        log.error("参数校验异常", e);
        String messages = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return Result.failure(messages);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result assertException(IllegalArgumentException e) {
        log.error("参数断言异常", e);
        return Result.failure(ErrorEnum.PARAMS_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return Result.failure(ErrorEnum.INTERNAL_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BindException.class)
    public Result bindException(BindException e) {
        log.error("其他异常", e);
        return Result.failure(ErrorEnum.PARAMS_ERROR);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result runtimeException(Exception e) {
        log.error("其他异常", e);
        return Result.failure(ErrorEnum.UNKNOWN_ERROR);
    }

}
