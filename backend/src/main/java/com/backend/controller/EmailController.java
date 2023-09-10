package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.service.EmailService;
import com.backend.utility.EmailInfo;

@RestController
@RequestMapping(value = "/api/v1/")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PreAuthorize("hasRole('ROLE_admin') or hasRole('ROLE_user')")
    @PostMapping(value = "sendEmail", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Boolean> sendEmail(@RequestBody EmailInfo emailInfo) {
        Boolean isMailSentSuccessfully = false;
        try {
            isMailSentSuccessfully = this.emailService.sendEmail(emailInfo);
        } catch (Exception e) {
            System.out.println("Error in sendEmail" + e.toString());
        }
        return ResponseEntity.ok(isMailSentSuccessfully);
    }

}