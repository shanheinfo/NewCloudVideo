package top.itshanhe.newcodevideo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import top.itshanhe.newcodevideo.common.constant.RedisConstants;
import top.itshanhe.newcodevideo.common.utils.RedisUtil;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.LoginDTO;
import top.itshanhe.newcodevideo.web.dto.SessionDTO;
import top.itshanhe.newcodevideo.web.entity.VideoUser;
import top.itshanhe.newcodevideo.web.mapper.VideoUserMapper;
import top.itshanhe.newcodevideo.web.service.IVideoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

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
//    @Autowired
//    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtil redisUtil;
    
    @Override
    public ResultUtil userMailLogin(LoginDTO loginDTO) {
        if (loginDTO.getSession().isEmpty()) {
            return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"非法数据");
        }
        if (loginDTO.getUserMail().isEmpty()) {
            return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"信息不完整");
        }
        if (loginDTO.getIFLogin()) {
            // todo 实际上当验证码通过之后，此账号是通过验证的了，但是由于要将数据存储在本地线程中，只能二次校验，但实际上是没必要的 需要单独写一个校验方法
            Map<String, Object> hashValue = redisUtil.getHashValue(RedisConstants.USER_CODE_KEY + loginDTO.getSession());
            SessionDTO sessionDTO = (SessionDTO) hashValue.get("sessionDTO");
            
            if (sessionDTO != null) {
            
            } else {
            
            }
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
