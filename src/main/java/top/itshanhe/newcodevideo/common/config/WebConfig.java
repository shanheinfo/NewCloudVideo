package top.itshanhe.newcodevideo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.itshanhe.newcodevideo.web.security.handler.CustomInterceptor;

import javax.annotation.Resource;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/16
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Resource
    private CustomInterceptor customInterceptor;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(5000);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，并设置拦截路径
        registry.addInterceptor(customInterceptor).addPathPatterns("/**")
        // 排除拦截的路径
        .excludePathPatterns("/public/**", "/userLogin/**");
    }
}
