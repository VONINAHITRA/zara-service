package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.dto.requests.EmailRequest;
import com.zara.zaraservice.security.JwtService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImpl {

    private final JavaMailSender mailSender;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    public EmailServiceImpl(JavaMailSender mailSender, JwtService jwtService, UserServiceImpl userService) {
        this.mailSender = mailSender;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public String sendEmail(EmailRequest emailRequest) {

        try {
            // Generate a token for the user
            String token = jwtService.generateToken(
                    emailRequest.getPhoneNumber(),
                    emailRequest.getFirstName(),
                    emailRequest.getLastName(),
                    emailRequest.getEmail(),
                    emailRequest.getAvatar(),
                    emailRequest.getId(),
                    emailRequest.getRole());

            Optional<UserDTO> optionalUser = userService.getUserByEmail(emailRequest.getEmail())
                    .stream()
                    .findFirst();

            Long userId = optionalUser.map(UserDTO::getId).orElse(null);

            if (userId == null) {
                throw new RuntimeException("User not found for email: " + emailRequest.getEmail());
            }

            // Generate activation link
            String activationLink = "http://localhost:8080/v1/api/users/" + userId + "/activate?token=" + token;

            // Create and send the email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailRequest.getEmail());
            message.setSubject(emailRequest.getLastName());
            message.setText("Bonjour,\n\nPour activer votre compte, cliquez sur le lien suivant : " +
                    activationLink +
                    "\n\nCordialement,\nL'équipe Zara Service");
            message.setFrom("noreply@zara-service.com");

            mailSender.send(message);

            return "Email d'activation envoyé avec succès.";
        } catch (Exception e) {
            return "Erreur lors de l'envoi de l'e-mail : " + e.getMessage();
        }
    }

    public String generateActivationLink(Long userId, String token) {
        String baseUrl = "http://localhost:4200/confirmation"; // Frontend URL
        return baseUrl + "?userId=" + userId + "&token=" + token;
    }

}
