package top.itshanhe.newcodevideo.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.LoginDTO;
import top.itshanhe.newcodevideo.web.dto.LoginUser;
import top.itshanhe.newcodevideo.web.service.IVideoUserService;

import javax.annotation.Resource;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/19
 */
@RestController
@RequestMapping("/web/user")
public class LoginController {
    
    @Resource
    private IVideoUserService userService;
    // 邮箱登录
    public ResultUtil loginMail(@RequestBody LoginDTO loginDTO) {
        return userService.userMailLogin(loginDTO);
    }
    // 手机号登录
    public ResultUtil loginPhone(@RequestBody LoginDTO loginDTO) {
        return userService.userPhoneLogin(loginDTO);
    }
    // 账号密码登录
    public ResultUtil loginPwd(@RequestBody LoginDTO loginDTO) {
        return userService.userPwdLogin(loginDTO);
    }
    public ResultUtil register(@RequestBody LoginDTO loginDTO) {
        return userService.register(loginDTO);
    }
    //todo 扫码登录
//    public ResultUtil LoginOA2(@RequestBody LoginDTO loginDTO) {
//        return userService.userOA2(loginDTO);
//    }
}
