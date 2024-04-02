package top.itshanhe.newcodevideo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebDTO {
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 账号
     */
    private String userName;
    
    /**
     * 昵称
     */
    private String userNickName;
    
    /**
     * 邮箱
     */
    private String userMail;
    
    /**
     * 手机号
     */
    private String userPhone;
    
    /**
     * 用户是否封禁
     */
    private Boolean userStatus;
    
    /**
     * 头像地址id,默认为默认头像地址
     */
    private Long userIcon;
}
