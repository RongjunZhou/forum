package cn.edu.njupt.forum.interceptor;

import cn.edu.njupt.forum.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    public static final ThreadLocal<UserInfo> userInfoHolder = new ThreadLocal<>();

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
//        String token = request.getHeader("token");
//        if (token == null || token.isBlank()) throw new LocalRuntimeException(ErrorEnum.WITHOUT_LOGIN);
//        Map<String, Claim> contentMap = JwtUtil.verifyAndGetClaim(token);
//        userInfoHolder.set(JsonUtil.parseObject(contentMap.get("userInfo").asString(), UserInfo.class));
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception e) {
        userInfoHolder.remove();
    }
}
