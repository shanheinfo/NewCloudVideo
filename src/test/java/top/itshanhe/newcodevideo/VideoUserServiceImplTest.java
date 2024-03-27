package top.itshanhe.newcodevideo;

import org.junit.jupiter.api.Test;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.LoginDTO;
import top.itshanhe.newcodevideo.web.service.impl.VideoUserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VideoUserServiceImplTest {
    
    @Test
    public void testUserMailLogin() {
        // 准备测试数据
        VideoUserServiceImpl userService = new VideoUserServiceImpl();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setSession("session_id");
        loginDTO.setUserMail("example@mail.com");
        loginDTO.setIFLogin(true); // 设置为登录模式
        
        // 调用被测试的方法
        ResultUtil result = userService.userMailLogin(loginDTO);
        
        // 验证返回的状态码是否为预期值
        assertEquals(200, result.getCode(), "返回的状态码应该为200，表示成功");
        
        // 如果希望进一步验证其他返回值，可以在此处添加更多的断言
    }
    
    @Test
    public void testUserMailLogin1() {
        // 准备测试数据
        VideoUserServiceImpl userService = new VideoUserServiceImpl();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setSession("session_id");
        loginDTO.setUserMail("example@mail.com");
        loginDTO.setIFLogin(true); // 设置为登录模式
        
        // 调用被测试的方法
        ResultUtil result = userService.userMailLogin(loginDTO);
        
        // 输出返回的数据
        System.out.println("返回的状态码：" + result.getCode());
        System.out.println("返回的消息：" + result.getMsg());
        System.out.println("返回的数据：" + result.getData());
        
        // 断言结果不为null
        assertNotNull(result);
    }
}
