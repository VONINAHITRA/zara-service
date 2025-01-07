package com.zara.zaraservice.service;

public interface EmailService {
    String sendEmail(String to, String subject, String body);
}
