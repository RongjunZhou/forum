package cn.edu.njupt.forum.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author: 風楪fy
 * @create: 2021-09-19 16:30
 **/
@Slf4j
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    public ResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //是否执行转换，默认为true即可
    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class converterType) {
        return true;
    }

    /**
     * 无入侵式的统一转化变量
     *
     * @param body                  方法返回值，即需要写入到响应流中
     * @param returnType            对应方法的返回值
     * @param selectedContentType   当前content-type
     * @param selectedConverterType 当前转化器
     * @param request               当前请求
     * @param response              当前响应
     * @return 处理后真正写入响应流的对象
     */
    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (body instanceof String) {
            try {
                response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                return objectMapper.writeValueAsString(GlobalResult.success(body));
            } catch (JsonProcessingException e) {
                log.error("json serialization error occur in response handler");
                return GlobalResult.failure("result json serialize error");
            }
        } else if (body instanceof GlobalResult) {
            return body;
        } else {
            return GlobalResult.success(body);
        }
    }
}
