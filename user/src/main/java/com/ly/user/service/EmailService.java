package com.ly.user.service;

public interface EmailService {
    /**
     * 发送验证码邮件
     *
     * @param to

     * @return
     */
    public boolean sendVerificationSimpleMail(String to);
}
