package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.DelaiContractuel;
import com.leoni.execution.contrats.Repositories.DelaiContractuelRepository;
import com.leoni.execution.contrats.Services.PenaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaliteServiceImpl implements PenaliteService {

    @Autowired
    private DelaiContractuelRepository delaiContractuelRepository;

    @Override
    public void calculerPenaliteAutomatiquement() {
        List<DelaiContractuel> delais = delaiContractuelRepository.findAll();

        for (DelaiContractuel delai : delais) {
            // 🔒 Ne rien faire si la date n'est pas encore dépassée
            if (delai.getDate() == null || delai.getDate().isAfter(LocalDate.now())) {
                delai.setJoursRetard(0);
                delai.setMontantTotal(0f);
                continue;
            }

            // ✅ Calcul du nombre de jours de retard
            long joursDeRetard = ChronoUnit.DAYS.between(delai.getDate(), LocalDate.now());
            delai.setJoursRetard((int) joursDeRetard);

            // 🧮 Calcul de la pénalité uniquement si non payée
            if (!Boolean.TRUE.equals(delai.getPenalitePayee())
                    && delai.getMontantPenaliteJournalier() != null
                    && delai.getMontantPenaliteJournalier() > 0) {

                float montantTotal = delai.getMontantPenaliteJournalier() * joursDeRetard;
                delai.setMontantTotal(montantTotal);
            } else {
                delai.setMontantTotal(0f);
            }

            // 💾 Enregistrement
            delaiContractuelRepository.save(delai);
        }
    }
}
