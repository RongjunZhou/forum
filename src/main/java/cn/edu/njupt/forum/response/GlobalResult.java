package cn.edu.njupt.forum.response;

import cn.edu.njupt.forum.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 風楪fy
 * @create: 2021-09-19 16:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResult<T> {
    private Boolean success;
    private String errMsg;
    private Integer errCode;
    private T data;

    public static <T> GlobalResult<T> success(T data) {
        return new GlobalResult<T>(true, null, null, data);
    }

    public static GlobalResult<Void> failure(String errMsg) {
        return new GlobalResult<>(false, errMsg, 5000, null);
    }

    public static GlobalResult<Void> failure(ErrorEnum error) {
        return new GlobalResult<>(false, error.getErrMsg(), error.getErrCode(), null);
    }

    public static GlobalResult<Void> failure(ErrorEnum error, String msg) {
        return new GlobalResult<>(false, msg, error.getErrCode(), null);
    }
}
