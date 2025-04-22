package com.leoni.execution.contrats.Services;

public interface NotificationService {

    /**
     * Méthode qui vérifie s'il faut envoyer des mails de notification
     * en fonction des conditions du cahier des charges :
     * - 7 jours avant la fin des contrats de type Travaux
     * - 2 mois avant la fin des contrats de type Continue
     * - 7 jours avant chaque délai contractuel (même ajouté après)
     */
    void envoyerNotificationsSiDateConcernee();
}
