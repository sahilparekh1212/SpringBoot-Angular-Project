package com.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.SomeCustomException;
import com.backend.service.EmailService;
import com.backend.util.Constants;
import com.backend.utility.EmailInfo;

@RestController
@RequestMapping(value = Constants.API_BASE_URL)
public class EmailController {

    @Autowired
    EmailService emailService;

    Logger logger = LogManager.getLogger(EmailController.class);

    @PreAuthorize("hasRole('ROLE_admin') or hasRole('ROLE_user')")
    @PostMapping(value = "sendEmail", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Boolean> sendEmail(@RequestBody EmailInfo emailInfo) {
        final String LOGEER_PREFIX = "In sendEmail:";
        Boolean isMailSentSuccessfully = false;
        try {
            isMailSentSuccessfully = this.emailService.sendEmail(emailInfo);
        } catch (Exception e) {
            // Catching generic exception for email details only
            logger.error("{} Error occured while sending email to={} with subject={}: message={}, stackTrace={}",
                    LOGEER_PREFIX,
                    emailInfo.getToEmail(), emailInfo.getSubject(),
                    e.getMessage(), e.getStackTrace());
            throw new SomeCustomException("Could not send email with emailInfo="
                    + (emailInfo != null ? emailInfo.getToEmail() : "emailInfo is null"));
        }
        return ResponseEntity.ok(isMailSentSuccessfully);
    }

}