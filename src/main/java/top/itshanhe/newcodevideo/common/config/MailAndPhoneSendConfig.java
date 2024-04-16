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
    public static String username;
    
    public void setUsername(String username) {
        MailAndPhoneSendConfig.username = username;
    }
    
    public static String getUserName() {
        return username;
    }
}