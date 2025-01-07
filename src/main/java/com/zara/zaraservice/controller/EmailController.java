package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.requests.EmailRequest;
import com.zara.zaraservice.service.impl.EmailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("emails")
public class EmailController {

    private final EmailServiceImpl emailServiceImpl;

    public EmailController(EmailServiceImpl emailServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        return ResponseEntity.ok(emailServiceImpl.sendEmail(emailRequest));
    }
}
