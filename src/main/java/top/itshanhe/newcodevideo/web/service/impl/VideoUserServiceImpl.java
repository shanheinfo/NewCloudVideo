package top.itshanhe.newcodevideo.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.http.HttpStatus;
import top.itshanhe.newcodevideo.common.constant.DataConstants;
import top.itshanhe.newcodevideo.common.constant.RedisConstants;
import top.itshanhe.newcodevideo.common.constant.RegexPatterns;
import top.itshanhe.newcodevideo.common.utils.*;
import top.itshanhe.newcodevideo.web.dto.LoginDTO;
import top.itshanhe.newcodevideo.web.dto.WebDTO;
import top.itshanhe.newcodevideo.web.entity.JwtTokens;
import top.itshanhe.newcodevideo.web.entity.VideoUser;
import top.itshanhe.newcodevideo.web.mapper.VideoUserMapper;
import top.itshanhe.newcodevideo.web.service.IJwtTokensService;
import top.itshanhe.newcodevideo.web.service.IVideoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
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
    private IJwtTokensService tokenService;

    
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
        String key = redisUtil.getStringValue(RedisConstants.USER_CAPTCHA_KEY + loginDTO.getSession());
        // 验证码
        if (loginDTO.getKey().isEmpty() || loginDTO.getKey().equals(key)) {
            return  new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"验证码错误");
        }
        // 当验证完之后，已经存在该用户，就是登录
        if (loginDTO.getIFLogin()) {
            try {
                // 获取redis 邮箱
                String sessionDTO = redisUtil.getStringValue(RedisConstants.USER_CODE_KEY + loginDTO.getSession());
                
                if (sessionDTO != null && sessionDTO == loginDTO.getUserMail()) {
                    VideoUser videoUser = lambdaQuery().eq(VideoUser::getUserMail,loginDTO.getUserMail()).one();
                    // 复制不敏感信息，转换为LoginDTO 然后复制为hash数据生成 jwt
                    WebDTO webDTO = new WebDTO();
                    // 复制数据
                    BeanUtil.copyProperties(videoUser, webDTO);
                    Map<String, Object> map = setTokenMap(sessionDTO, webDTO);
                    // 生成JWT
                    String jwt = JwtUtil.generateJwt(map);
                    
                    return new ResultUtil<>(HttpStatus.OK.value(),"登录成功",jwt);
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
    
        Map<String, Object> userMap = setTokenMap(uid,videoUser);

        // 将不敏感数据 创建jwt
        String jwt = JwtUtil.generateJwt(userMap);
        
        // 补全基本信息
        videoUser.setUserPwd(Md5Util.Md5Code(RandomUtil.randomString(8)));
        videoUser.setUserCreateTime(LocalDateTime.now());
        videoUser.setUserUpdateTime(LocalDateTime.now());
        videoUser.setUserCreateIp(loginDTO.getIp());
        //保存用户
        save(videoUser);
        return new ResultUtil<>(HttpStatus.OK.value(),"注册成功",jwt);
    }
    
    /**
     * 将用户token存入数据库并返回map
     * @param uid 用户id
     * @param webDTO 用户类
     * @return hashMap
     * @param <T>
     */
    private <T> Map<String, Object> setTokenMap(String uid, T webDTO) {
        Map<String, Object> map = BeanUtil.beanToMap(webDTO,new HashMap<>(), CopyOptions.create()
                .setIgnoreNullValue(true)
                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // token 解决jwt 有状态处理
        String token = IdUtil.fastUUID()+ RandomUtil.randomString(5);
        map.put("token",token);
        // 将token存入数据库
        JwtTokens jwtTokens = new JwtTokens();
        jwtTokens.setUserId(uid);
        // 设置为7天后过期
        jwtTokens.setExpirationTime(LocalDateTime.now().plus(7, ChronoUnit.DAYS));
        jwtTokens.setToken(token);
        // 存入token数据库
        tokenService.save(jwtTokens);
        redisUtil.putHashValue(map,RedisConstants.USER_TOKEN_KEY + uid);
        // 4小时过期
        redisUtil.expire(RedisConstants.USER_TOKEN_KEY + uid, 4, TimeUnit.HOURS);
        return map;
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
    
    /**
     * 验证码和临时session凭证
     * @param loginDTO
     * @param num 1 是邮箱 2 是手机号
     * @return 验证码和session
     */
    public ResultUtil temporarySession(LoginDTO loginDTO,int num) {
        String code = RandomUtil.randomNumbers(6);
        return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"信息不完整");
    }
    
    @Override
    public ResultUtil sendCaptcha(LoginDTO loginDTO) {
        // 验证邮箱和手机号
        if (!loginDTO.getUserMail().isEmpty() && !RegexUtils.isEmailInvalid(loginDTO.getUserMail())) {
            return temporarySession(loginDTO,1);
        }
        if (!loginDTO.getUserPhone().isEmpty() && !RegexUtils.isPhoneInvalid(loginDTO.getUserPhone())) {
            return temporarySession(loginDTO,2);
        }
        return new ResultUtil<>(HttpStatus.FORBIDDEN.value(),"信息不完整");
    }
}
