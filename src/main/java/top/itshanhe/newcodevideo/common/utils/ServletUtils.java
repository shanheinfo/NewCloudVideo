package top.itshanhe.newcodevideo.common.utils;

import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 局部跨域工具类，如果已经配置了全局工具类，就不需要了
 * </p>
 *
 * @author shanhe
 * @date 2024/3/14
 */
public class ServletUtils {
    
    /**
     * 渲染到客户端
     *
     * @param object   待渲染的实体类，会自动转为json
     */
    public static void render(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        
        response.getWriter().print(JSONUtil.toJsonStr(object));
    }
}
