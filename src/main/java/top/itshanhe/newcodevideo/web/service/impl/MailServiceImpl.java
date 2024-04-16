package top.itshanhe.newcodevideo.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.itshanhe.newcodevideo.common.config.MailAndPhoneSendConfig;
import top.itshanhe.newcodevideo.web.service.MailService;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/4/3
 */
@Service
public class MailServiceImpl implements MailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    
    
    /**
     * 发送HTML格式的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @return true or false
     */
    @Override
    public Boolean sendHtmlMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(MailAndPhoneSendConfig.username);
            //邮件接收人
            messageHelper.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
