package top.itshanhe.newcodevideo.web.security.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import top.itshanhe.newcodevideo.common.utils.JsonUtil;
import top.itshanhe.newcodevideo.common.utils.JwtUtil;
import top.itshanhe.newcodevideo.common.utils.RedisUtil;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.LoginUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = (String) claims.get("userId");
        } catch (Exception e) {
            //处理异常
            ResultUtil result = new ResultUtil(HttpStatus.UNAUTHORIZED.value(),"令牌过期，重新登陆");
            String json = JSON.toJSONString(result);
            JsonUtil.renderString(response,json);
            return;
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //获取权限信息封装到Authentication中
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        //存入SecurityContextHolder 将当前线程的信息存在内存中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
