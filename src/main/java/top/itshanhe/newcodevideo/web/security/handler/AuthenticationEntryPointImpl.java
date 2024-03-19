package top.itshanhe.newcodevideo.web.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.itshanhe.newcodevideo.common.utils.JsonUtil;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 未认证
 * </p>
 *
 * @author shanhe
 * @date 2024/3/16
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResultUtil result = new ResultUtil(HttpStatus.UNAUTHORIZED.value(),"用户认证失败");
        String json = JSON.toJSONString(result);
        //处理异常
        JsonUtil.renderString(response,json);
    }
}
