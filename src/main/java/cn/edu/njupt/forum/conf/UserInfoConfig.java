package cn.edu.njupt.forum.conf;

import cn.edu.njupt.forum.annotation.Info;
import cn.edu.njupt.forum.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
public class UserInfoConfig {
    @Bean
    public HandlerMethodArgumentResolver userInfoArgumentResolver() {
        return new HandlerMethodArgumentResolver() {

            /**
             * 注意类型符合
             * @param parameter
             * @return
             */
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(Info.class);
            }

            //当支持后进行相应的转换
            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
                return AuthInterceptor.userInfoHolder.get();
            }
        };
    }
}
