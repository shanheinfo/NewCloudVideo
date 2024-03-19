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
    
    //创建BCryptPasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    
    
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 配置HttpSecurity
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        // 定义授权规则
                        .antMatchers("/public/**").permitAll()  // 允许访问以 "/public/" 开头的所有路径，不需要进行身份验证
                        .anyRequest().authenticated()  // 对于其他所有请求，需要进行身份验证
                )
                //关闭 csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //登录接口允许匿名访问
                .authorizeRequests()
                .antMatchers("/user/login").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                .logout()  // 配置注销功能
                .logoutUrl("/logout")
                //清除认证信息
                .clearAuthentication(true)
                // 允许所有用户访问注销功能
                .permitAll();
//                .formLogin()  // 配置表单登录
//                .loginPage("/login")  // 指定登录页面的路径为 "/login"
//                .permitAll()  // 允许所有用户访问登录页面
//                .and()  // 连接前面的配置和后面的配置
//                .logout()  // 配置注销功能
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .permitAll();  // 允许所有用户访问注销功能
        //允许跨域
        httpSecurity.cors();
    
        //添加过滤器
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    
        //配置异常处理器
        httpSecurity.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        return httpSecurity.build();
    }
    
    
}
