package com.myproject.journalApp.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setText(body);
            mail.setSubject(subject);
            javaMailSender.send(mail);
            log.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            log.error("Exception while sending mail", e);
        }
    }
}