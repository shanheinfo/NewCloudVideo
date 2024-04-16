package top.itshanhe.newcodevideo.common.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.itshanhe.newcodevideo.web.security.filter.JwtAuthenticationTokenFilter;


/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/13
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    
    
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .antMatchers("/public/**").permitAll()  // 允许访问以 "/public/" 开头的所有路径，不需要进行身份验证
                        .antMatchers("/userLogin/**").permitAll()  // 允许访问登录接口，不需要进行身份验证
                        .antMatchers("/captcha/**").permitAll()  // 允许访问验证码接口，不需要进行身份验证
                        .anyRequest().authenticated()  // 对于其他所有请求，需要进行身份验证
                )
                .csrf().disable()  // 关闭 CSRF
                // 禁用HTTP响应标头
                .headers().cacheControl().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不使用 Session 获取 SecurityContext
                .and()
                .logout()  // 配置注销功能
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .permitAll();  // 允许所有用户访问注销功能
    
        httpSecurity.cors(); // 允许跨域
    
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // 添加过滤器
    
        // 配置异常处理器
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint) // 配置认证失败处理器
                .accessDeniedHandler(accessDeniedHandler); // 配置访问被拒绝处理器
    
        return httpSecurity.build();
    }
}
