package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-test")
    public String envoyerMailTest(@RequestParam String to,
                                  @RequestParam String sujet,
                                  @RequestParam String message) {
        try {
            emailService.envoyerMailPersonnesDediees(to, sujet, message);
            return "✅ E-mail envoyé avec succès à : " + to;
        } catch (Exception e) {
            return "❌ Échec de l'envoi : " + e.getMessage();
        }
    }
}
