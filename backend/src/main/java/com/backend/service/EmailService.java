package com.backend.service;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.backend.controller.EmailController;
import com.backend.utility.EmailInfo;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String FROM_EMAIL = "TBC";

    Logger logger = LogManager.getLogger(EmailService.class);

    public Boolean sendEmail(EmailInfo emailInfo) {
        final String LOGEER_PREFIX = "In sendEmail:";
        Boolean isEmailSentSuccessfully = true;
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(FROM_EMAIL);
            email.setTo(emailInfo.getToEmail());
            email.setSubject(emailInfo.getSubject());
            email.setText(emailInfo.getBody());
            javaMailSender.send(email);
        } catch (Exception e) {
            isEmailSentSuccessfully = false;
            logger.error("{} Error occured while sending email to={}, from={}, subject={}: , message:{}, stackTrace:{}",
                    LOGEER_PREFIX,
                    emailInfo.getToEmail(), FROM_EMAIL, emailInfo.getSubject(),
                    e.getMessage(), e.getStackTrace());
        }
        return isEmailSentSuccessfully;
    }
}
