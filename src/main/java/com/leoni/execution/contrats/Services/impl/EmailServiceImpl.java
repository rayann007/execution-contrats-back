package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void envoyerMailPersonnesDediees(String emails, String sujet, String contenu) {
        if (emails == null || emails.isBlank()) return;

        for (String email : emails.split(",")) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.trim());
            message.setSubject(sujet);
            message.setText(contenu);
            mailSender.send(message);
        }
    }
}
