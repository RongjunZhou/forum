package cn.edu.njupt.forum.util;

import cn.edu.njupt.forum.model.UserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET = "JA02JS993J239FKKRVKCLSGFS";

    public static String generateJwt(UserInfo userInfo){
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR, 24);
        JWTCreator.Builder builder = JWT.create();
        String json = JsonUtil.toJson(userInfo);
        builder.withClaim("userInfo", json);
        builder.withClaim("timestamp", System.currentTimeMillis());
        builder.withExpiresAt(time.getTime());
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Claim> verifyAndGetClaim(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaims();
    }
}
