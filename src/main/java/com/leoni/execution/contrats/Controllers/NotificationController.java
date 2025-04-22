package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint pour déclencher l’envoi manuellement (utile pour tester)
    @PostMapping("/envoyer")
    public String envoyerNotifications() {
        notificationService.envoyerNotificationsSiDateConcernee();
        return "Notifications envoyées si conditions remplies";
    }
}
