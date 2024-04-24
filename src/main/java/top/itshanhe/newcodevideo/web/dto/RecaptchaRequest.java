package top.itshanhe.newcodevideo.web.dto;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/4/23
 */
public class RecaptchaRequest {
    private String recaptchaToken;
    
    public String getRecaptchaToken() {
        return recaptchaToken;
    }
    
    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }
}

