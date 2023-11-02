package cn.edu.njupt.forum.exception;


import cn.edu.njupt.forum.enums.ErrorEnum;
import cn.edu.njupt.forum.response.GlobalResult;
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
    public GlobalResult localRunTimeException(LocalRuntimeException e) {
        //LocalRuntimeException本意其实是给用户看的错误信息，没有必要打印全栈帧
        //这里打出第一个栈帧的用处其实是为了用于告诉开发者哪一行出错了，不然没有栈帧，对于一些逻辑错误可能会比较难调试
        log.error("""
                error msg: {}
                first stackTrace: {}
                """, e.getMessage(), e.getStackTrace()[0]);
        if (e.getError() != null) {
            return GlobalResult.failure(e.getError(), e.getMessage());
        } else {
            return GlobalResult.failure(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalResult handlerValidationException(MethodArgumentNotValidException e) {
        log.error("参数校验异常", e);
        String messages = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return GlobalResult.failure(messages);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public GlobalResult assertException(IllegalArgumentException e) {
        log.error("参数断言异常", e);
        return GlobalResult.failure(ErrorEnum.PARAMS_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public GlobalResult nullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return GlobalResult.failure(ErrorEnum.INTERNAL_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BindException.class)
    public GlobalResult bindException(BindException e) {
        log.error("其他异常", e);
        return GlobalResult.failure(ErrorEnum.PARAMS_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GlobalResult runtimeException(Exception e) {
        log.error("其他异常", e);
        return GlobalResult.failure(ErrorEnum.UNKNOWN_ERROR);
    }

}
