package top.itshanhe.newcodevideo.web.service;

import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.LoginDTO;
import top.itshanhe.newcodevideo.web.entity.VideoUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
public interface IVideoUserService extends IService<VideoUser> {
    
    ResultUtil userMailLogin(LoginDTO loginDTO);
    
    ResultUtil userPhoneLogin(LoginDTO loginDTO);
    
    ResultUtil userPwdLogin(LoginDTO loginDTO);
}
