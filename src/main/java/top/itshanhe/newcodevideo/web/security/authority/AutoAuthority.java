package top.itshanhe.newcodevideo.web.security.authority;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.itshanhe.newcodevideo.web.dto.LoginUser;

import java.util.List;

/**
 * <p>
 * 权限管理
 * </p>
 *
 * @author shanhe
 * @date 2024/3/20
 */
@Component("ex")
public class AutoAuthority {
    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取权限集合
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在指定权限
        return permissions.contains(authority);
    }
}
