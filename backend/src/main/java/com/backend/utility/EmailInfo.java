package com.backend.utility;

public class EmailInfo {

    private String toEmail;
    private String subject;
    private String body;

    public String getToEmail() {
        return this.toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
