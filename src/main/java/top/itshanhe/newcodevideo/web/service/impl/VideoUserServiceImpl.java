package top.itshanhe.newcodevideo.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import top.itshanhe.newcodevideo.common.constant.DataConstants;
import top.itshanhe.newcodevideo.common.constant.RedisConstants;
import top.itshanhe.newcodevideo.common.utils.JwtUtil;
import top.itshanhe.newcodevideo.common.utils.Md5Util;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
            
            try {
                // 获取redis id
                String sessionDTO = redisUtil.getStringValue(RedisConstants.USER_CODE_KEY + loginDTO.getSession());
                
                if (sessionDTO != null) {
                    VideoUser videoUser = lambdaQuery().eq(VideoUser::getUserId,sessionDTO).one();
                    // 复制不敏感信息，转换为LoginDTO 然后复制为hash数据生成 jwt
                    LoginDTO loginDTO1 = new LoginDTO();
                    BeanUtil.copyProperties(videoUser, loginDTO1);
                    Map<String, Object> map = BeanUtil.beanToMap(loginDTO1,new HashMap<>(), CopyOptions.create()
                            .setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
                    // redis 存入用户信息
                    
                    // 生成Jwt
                    
                    return new ResultUtil<>(HttpStatus.OK.value(),"登录成功");
                } else {
                    return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"登陆失败");
                }
            } catch (Exception e) {
                return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"登陆失败");
            }
        }
        
        // 注册用户
        VideoUser videoUser = new VideoUser();
        videoUser.setUserMail(loginDTO.getUserMail());
        String uid = DataConstants.USER_HEAD +IdUtil.objectId();
        videoUser.setAlipayUserId(uid);
        videoUser.setUserNickName(DataConstants.USER_HEAD + RandomUtil.randomString(10));
        videoUser.setUserName(loginDTO.getUserMail());
        videoUser.setUserStatus(false);
    
        Map<String,Object> userMap = BeanUtil.beanToMap(videoUser,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        redisUtil.putHashValue(userMap,RedisConstants.USER_TOKEN_KEY + uid);
        // 一小时过期
        redisUtil.expire(RedisConstants.USER_TOKEN_KEY + uid, 1, TimeUnit.HOURS);
        // 将不敏感数据 创建jwt
        String jwt = JwtUtil.generateJwt(userMap);
    
        videoUser.setUserPwd(Md5Util.Md5Code(RandomUtil.randomString(8)));
        videoUser.setUserCreateTime(LocalDateTime.now());
        videoUser.setUserUpdateTime(LocalDateTime.now());
        
       
        return new ResultUtil<>(HttpStatus.OK.value(),"注册成功",jwt);
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
