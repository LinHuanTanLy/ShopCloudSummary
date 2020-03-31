package com.ly.user.service.impl;

import com.ly.commom.utils.CommUtils;
import com.ly.user.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public boolean sendVerificationSimpleMail(String to) {
        String verificationCode = CommUtils.initVerificationCode();
        redisTemplate.opsForValue().set(to, verificationCode, 5 * 60, TimeUnit.SECONDS);
        log.info("存进redis的验证码是" + verificationCode);
        String subject = "注册验证码";
        String content = "您的验证码是" + verificationCode + "，该验证码5分钟内有效";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            mailSender.send(simpleMailMessage);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }

    }
}
