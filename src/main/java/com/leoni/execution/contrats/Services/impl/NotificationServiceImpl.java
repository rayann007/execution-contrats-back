package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.DelaiContractuel;
import com.leoni.execution.contrats.Models.TypeContrat;
import com.leoni.execution.contrats.Repositories.ContratRepository;
import com.leoni.execution.contrats.Repositories.DelaiContractuelRepository;
import com.leoni.execution.contrats.Services.EmailService;
import com.leoni.execution.contrats.Services.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private DelaiContractuelRepository delaiContractuelRepository;

    @Autowired
    private EmailService emailService;

    /**
     * 🔔 Méthode à appeler quand la date correspond à un événement de notification
     */
    @Override
    @Transactional
    public void envoyerNotificationsSiDateConcernee() {
        LocalDate today = LocalDate.now();

        // 📅 Contrats TRAVAUX : notifier 7 jours avant la fin
        List<Contrat> contratsTravaux = contratRepository.findByType(TypeContrat.Travaux);
        for (Contrat c : contratsTravaux) {
            if (c.getDateFin() != null && c.getDateFin().minusDays(7).isEqual(today)) {
                emailService.envoyerMailPersonnesDediees(
                        c.getEmailsPersonnesDediees(),
                        "📢 Fin de contrat imminente",
                        "Le contrat '" + c.getNomContrat() + "' se termine dans 7 jours."
                );
            }
        }

        // 📅 Contrats CONTINUE : notifier 2 mois avant la fin
        List<Contrat> contratsContinue = contratRepository.findByType(TypeContrat.Continue);
        for (Contrat c : contratsContinue) {
            if (c.getDateFin() != null && c.getDateFin().minusMonths(2).isEqual(today)) {
                emailService.envoyerMailPersonnesDediees(
                        c.getEmailsPersonnesDediees(),
                        "📢 Fin prochaine d’un contrat continu",
                        "Le contrat continu '" + c.getNomContrat() + "' se termine dans 2 mois."
                );
            }
        }

        // 📅 Délais contractuels : notifier 7 jours avant le délai
        List<DelaiContractuel> delais = delaiContractuelRepository.findAll();
        for (DelaiContractuel delai : delais) {
            if (delai.getDate() != null && delai.getDate().minusDays(7).isEqual(today)) {
                Contrat contrat = delai.getContrat();
                emailService.envoyerMailPersonnesDediees(
                        contrat.getEmailsPersonnesDediees(),
                        "📢 Délai contractuel imminent",
                        "Un délai approche pour le contrat '" + contrat.getNomContrat() + "' :\n" +
                                delai.getCommentaire()
                );
            }
        }
    }
}
