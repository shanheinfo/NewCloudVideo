package top.itshanhe.newcodevideo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/6
 */
public class JwtUtil {
    
    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "shanhe@web";
    
    

    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, JWT_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)))
                .compact();
        return jwt;
    }


    
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        return Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
