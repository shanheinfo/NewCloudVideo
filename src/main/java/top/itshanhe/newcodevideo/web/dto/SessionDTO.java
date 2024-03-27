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
 * @date 2024/3/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    // 用户id
    private String userId;
    
    // 账号
    private String userName;
    
    // 昵称
    private String userNickName;
    
    // 邮箱
    private String userMail;
    
    // 手机号
    private String userPhone;
    
    // 密码
    private String userPwd;
    
    // 粉丝数
    private Integer userFans;
    
    // 关注数
    private Integer userFollow;
    
    // 余额
    private Double moneyData;
    
    // 头像地址id,默认为默认头像地址
    private Long userIcon = 1L;
    
    // 用户是否封禁
    private Boolean userStatus;
    
}
