package top.itshanhe.newcodevideo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 登录用户DTO
 * </p>
 *
 * @author shanhe
 * @date 2024/3/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    //用户名
    private String userName;
    //密码
    private String userMail;
    //手机号
    private String userPhone;
    //密码
    private String userPwd;
    //验证码
    private String key;
    //临时session
    private String session;
    //判断是否登录
    private Boolean IFLogin = true;
}
