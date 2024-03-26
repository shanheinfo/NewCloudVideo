package top.itshanhe.newcodevideo.web.service.impl;

import org.springframework.http.HttpStatus;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.LoginDTO;
import top.itshanhe.newcodevideo.web.entity.VideoUser;
import top.itshanhe.newcodevideo.web.mapper.VideoUserMapper;
import top.itshanhe.newcodevideo.web.service.IVideoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Service
public class VideoUserServiceImpl extends ServiceImpl<VideoUserMapper, VideoUser> implements IVideoUserService {
    
    @Resource
    private VideoUserMapper userMapper;
    
    @Override
    public ResultUtil userMailLogin(LoginDTO loginDTO) {
        if (loginDTO.getSession().isEmpty()) {
            return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"非法数据");
        }
        if (loginDTO.getUserMail().isEmpty()) {
            return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"信息不完整");
        }
        if (loginDTO.getIFLogin()) {
        
        }
        return new ResultUtil<>(HttpStatus.OK.value(),"注册成功");
    }
    
    @Override
    public ResultUtil userPhoneLogin(LoginDTO loginDTO) {
        return null;
    }
    
    @Override
    public ResultUtil userPwdLogin(LoginDTO loginDTO) {
        return null;
    }
    
    @Override
    public ResultUtil register(LoginDTO loginDTO) {
        
        return null;
    }
}
