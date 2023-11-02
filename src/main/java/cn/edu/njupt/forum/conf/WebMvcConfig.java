package cn.edu.njupt.forum.conf;

import cn.edu.njupt.forum.interceptor.AuthInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final HandlerMethodArgumentResolver userInfoArgumentResolver;

    public WebMvcConfig(AuthInterceptor authInterceptor, HandlerMethodArgumentResolver userInfoArgumentResolver) {
        this.authInterceptor = authInterceptor;
        this.userInfoArgumentResolver = userInfoArgumentResolver;
    }

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "register");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoArgumentResolver);
    }
}
