package top.itshanhe.newcodevideo.web.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.itshanhe.newcodevideo.common.utils.JsonUtil;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 权限不足
 * </p>
 *
 * @author shanhe
 * @date 2024/3/16
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultUtil result = new ResultUtil(HttpStatus.FORBIDDEN.value(),"没有权限");
        String json = JSON.toJSONString(result);
        //处理异常
        JsonUtil.renderString(response,json);
    }
}
