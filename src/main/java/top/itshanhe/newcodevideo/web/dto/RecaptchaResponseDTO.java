package top.itshanhe.newcodevideo.web.dto;

import lombok.Data;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/4/10
 */
@Data
public class RecaptchaResponseDTO {
    private boolean success;
    private String hostname;
}
