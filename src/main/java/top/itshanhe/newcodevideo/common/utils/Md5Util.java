package top.itshanhe.newcodevideo.common.utils;

import org.springframework.util.DigestUtils;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/6
 */
public class Md5Util {
    static String salt= "shanhe-=+video";
    
    public static String Md5Code(String password) {
        String saltPassword=salt+"/"+password;
        String md5Password = DigestUtils.md5DigestAsHex(saltPassword.getBytes());
        return md5Password;
    }
    
}
