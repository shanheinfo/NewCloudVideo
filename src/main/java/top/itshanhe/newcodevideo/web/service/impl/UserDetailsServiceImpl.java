package top.itshanhe.newcodevideo.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.itshanhe.newcodevideo.common.constant.UserTypeEnum;
import top.itshanhe.newcodevideo.web.dto.LoginUser;
import top.itshanhe.newcodevideo.web.entity.VideoAdmin;
import top.itshanhe.newcodevideo.web.entity.VideoUser;
import top.itshanhe.newcodevideo.web.mapper.VideoAdminMapper;
import top.itshanhe.newcodevideo.web.mapper.VideoUserMapper;
import top.itshanhe.newcodevideo.web.service.IVideoUserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/19
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private VideoUserMapper userMapper;
    @Resource
    private VideoAdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<VideoUser> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(VideoUser::getUserName, username);
        VideoUser user = userMapper.selectOne(userQueryWrapper);
    
        if (user != null) {
            // 判断用户是否被封禁
            if (user.getUserStatus()) {
//                throw new DisabledException("用户已被封禁");
            }
        
            // 如果是管理员，检查管理员权限
            LambdaQueryWrapper<VideoAdmin> adminQueryWrapper = new LambdaQueryWrapper<>();
            adminQueryWrapper.eq(VideoAdmin::getUserId, user.getUserId());
            VideoAdmin admin = adminMapper.selectOne(adminQueryWrapper);
            if (admin != null && admin.getType() == 1) {
                // 在本项目中，普通用户也可以具有管理员权限，所以如果是管理员则有两个权限同时存在
                List<String> permissions = new ArrayList<>();
                // 管理员权限
                permissions.add(UserTypeEnum.getValue(UserTypeEnum.ADMIN));
                // 给用户权限
                permissions.add(UserTypeEnum.getValue(UserTypeEnum.USER));
                return new LoginUser(user, permissions);
            }
        }
    
        // 普通用户
        List<String> permissions = new ArrayList<>();
        permissions.add(UserTypeEnum.getValue(UserTypeEnum.USER));
        return new LoginUser(user, permissions);
    }
}
