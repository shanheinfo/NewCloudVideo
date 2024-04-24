package top.itshanhe.newcodevideo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import top.itshanhe.newcodevideo.common.utils.ResultUtil;
import top.itshanhe.newcodevideo.web.dto.RecaptchaRequest;
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
    @Autowired
    private RestTemplate restTemplate;
    private final String RECAPTCHA_SECRET = "6LfTXrYpAAAAAGzaGTEo69Ghjh962T1WNX6XySwI";
    
    @PostMapping("/captcha/verify-recaptcha")
    public ResponseEntity<?> verifyRecaptcha(@RequestBody RecaptchaRequest recaptchaRequest) {
        String recaptchaToken = recaptchaRequest.getRecaptchaToken();
        log.info("这是验证码{}",recaptchaToken);
        String url = "https://recaptcha.net/recaptcha/api/siteverify";
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("secret", RECAPTCHA_SECRET)
                .queryParam("response", recaptchaToken);
        
        String fullUrl = uriBuilder.toUriString();
        try {
            ResponseEntity<RecaptchaResponseDTO> responseEntity = restTemplate.postForEntity(fullUrl, null, RecaptchaResponseDTO.class);
            RecaptchaResponseDTO response = responseEntity.getBody();
            if (response != null && response.isSuccess()) {
                return ResponseEntity.ok(new ResultUtil<>(200, "验证成功"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultUtil<>(502, "不安全的用户"));
            }
        } catch (Exception e) {
            log.error("Error verifying reCAPTCHA: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResultUtil<>(500, "Internal Server Error"));
        }
    }



    
}