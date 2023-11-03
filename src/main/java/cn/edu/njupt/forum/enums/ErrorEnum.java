package cn.edu.njupt.forum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    INTERNAL_ERROR(5000, "内部错误"),
    UNKNOWN_ERROR(5001, "未知错误"),
    JSON_ERROR(5003, "JSON解析失败"),
    PASSWORD_ERROR(4001, "密码错误"),
    USER_NOT_EXIST(4002, "用户不存在"),
    PARAMS_ERROR(4000, "参数错误"),
    WITHOUT_LOGIN(3000, "未登录"),
    ;

    private final Integer errCode;
    private final String errMsg;

}
