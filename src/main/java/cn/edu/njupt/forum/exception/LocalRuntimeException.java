package cn.edu.njupt.forum.exception;

import cn.edu.njupt.forum.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: 風楪fy
 * @create: 2021-09-19 16:50
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("all")
public class LocalRuntimeException extends RuntimeException {

    private ErrorEnum error;

    //默认错误
    public LocalRuntimeException(String message) {
        super(message, null);
//        super(message,null,false,false);
    }

    public LocalRuntimeException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMsg(), null);
        this.error = errorEnum;
    }


    public LocalRuntimeException(ErrorEnum error, String message) {
        super(error.getErrMsg() + ":" + message, null);
        this.error = error;
    }

    public LocalRuntimeException(ErrorEnum error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public LocalRuntimeException(ErrorEnum error, Throwable cause) {
        super(null, cause);
        this.error = error;
    }
}
