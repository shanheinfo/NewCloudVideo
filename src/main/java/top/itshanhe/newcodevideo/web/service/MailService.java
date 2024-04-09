package top.itshanhe.newcodevideo.web.service;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/4/3
 */
public interface MailService {
    Boolean sendHtmlMail(String to, String subject, String content);
}
