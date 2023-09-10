package com.backend.service;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.backend.utility.EmailInfo;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private final String FROM_EMAIL = "";

    public Boolean sendEmail(EmailInfo emailInfo) {
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
            System.out.println("Error -> EmailService -> sendEmail -> " + e.toString());
        }
        return isEmailSentSuccessfully;
    }
}
