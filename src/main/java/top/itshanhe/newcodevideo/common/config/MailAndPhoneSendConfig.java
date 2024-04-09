package top.itshanhe.newcodevideo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 配置类
 * </p>
 *
 * @author shanhe
 * @date 2024/4/3
 */
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailAndPhoneSendConfig {
    private String username;
    
    // getter 和 setter 方法
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}
