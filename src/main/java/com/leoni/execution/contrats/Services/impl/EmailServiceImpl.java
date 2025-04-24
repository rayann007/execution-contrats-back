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

        // 📬 Adresses à mettre en copie pour le service juridique
        String[] copieJuridique = {
                "moncef.touati@leoni.com",
                "MOHAMED-ALI.HAGUI@leoni.com"
        };

        for (String email : emails.split(",")) {
            String destinataire = email.trim();
            if (!destinataire.isBlank()) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(destinataire);               // Destinataire principal
                message.setSubject(sujet);                 // Sujet du mail
                message.setText(contenu);                  // Contenu du mail
                message.setCc(copieJuridique);             // 📌 Copie aux responsables juridiques

                mailSender.send(message);
            }
        }
    }
}
