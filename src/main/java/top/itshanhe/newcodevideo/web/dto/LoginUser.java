package top.itshanhe.newcodevideo.web.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.itshanhe.newcodevideo.web.entity.VideoUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义用户信息
 * </p>
 *
 * @author shanhe
 * @date 2024/3/16
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    
    // 用户信息
    private VideoUser user;
    
    // 用户权限列表
    private List<String> permissions;
    

    public LoginUser(VideoUser user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }
    
    // Spring Security 权限列表
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;
    
    // 获取用户的权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 如果权限列表已经存在，则直接返回
        if (authorities != null) {
            return authorities;
        }
        // 如果权限列表不存在，则根据权限字符串列表创建权限对象列表
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }
    
    // 获取用户密码
    @Override
    public String getPassword() {
        return user.getUserPwd();
    }
    
    // 获取用户用户名
    @Override
    public String getUsername() {
        return user.getUserName();
    }
    
    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    // 账户是否未被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    // 凭据是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    // 账户是否启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}

