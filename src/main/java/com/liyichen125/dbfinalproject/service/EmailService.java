package com.liyichen125.dbfinalproject.service;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
}
