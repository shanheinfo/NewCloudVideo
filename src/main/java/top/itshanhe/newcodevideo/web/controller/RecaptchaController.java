package top.itshanhe.newcodevideo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.RecaptchaResponseDTO;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/4/10
 */
@Slf4j
@RestController
public class RecaptchaController {
    
    private final String RECAPTCHA_SECRET = "6LfTXrYpAAAAAGzaGTEo69Ghjh962T1WNX6XySwI";
    
    @PostMapping("/captcha/verify-recaptcha")
    public ResultUtil verifyRecaptcha(@RequestBody String recaptchaToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://recaptcha.net/recaptcha/api/siteverify";
        String params = "?secret=" + RECAPTCHA_SECRET + "&response=" + recaptchaToken;
        String fullUrl = url + params;
        RecaptchaResponseDTO response = restTemplate.postForObject(fullUrl, null, RecaptchaResponseDTO.class);
        log.info("test");
        if (response != null && response.isSuccess()) {
            // reCAPTCHA 验证成功
            return new ResultUtil<>(200,"验证成功");
        } else {
            // reCAPTCHA 验证失败
            return new ResultUtil<>(502,"不安全的用户");
        }
    }
    
}