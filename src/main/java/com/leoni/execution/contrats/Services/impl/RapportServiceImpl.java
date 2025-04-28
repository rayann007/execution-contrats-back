package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.Rapport;
import com.leoni.execution.contrats.Repositories.ContratRepository;
import com.leoni.execution.contrats.Repositories.EvaluationRepository;
import com.leoni.execution.contrats.Repositories.RapportRepository;
import com.leoni.execution.contrats.Services.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RapportServiceImpl implements RapportService {

    @Autowired
    private RapportRepository rapportRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ContratRepository contratRepository;

    @Override
    public Rapport genererRapportPourContrat(Long contratId) {
        // Vérifier si un rapport existe déjà pour ce contrat
        if (rapportRepository.existsByContratId(contratId)) {
            throw new RuntimeException("Un rapport a déjà été généré pour ce contrat !");
        }

        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé."));

        long total = evaluationRepository.findByContratId(contratId).size();
        long verts = evaluationRepository.findByContratId(contratId).stream()
                .filter(e -> e.getCouleurIndicateur().name().equals("Vert"))
                .count();
        long jaunes = evaluationRepository.findByContratId(contratId).stream()
                .filter(e -> e.getCouleurIndicateur().name().equals("Jaune"))
                .count();
        long oranges = evaluationRepository.findByContratId(contratId).stream()
                .filter(e -> e.getCouleurIndicateur().name().equals("Orange"))
                .count();
        long rouges = evaluationRepository.findByContratId(contratId).stream()
                .filter(e -> e.getCouleurIndicateur().name().equals("Rouge"))
                .count();

        String tauxRespect = total > 0 ? (verts * 100 / total) + "%" : "0%";

        String contenu = """
                Rapport du contrat : %s
                Date de génération : %s

                ✔ Total des délais : %d
                ✔ Délais respectés (verts) : %d
                ✔ Délais jaunes : %d
                ✔ Délais oranges : %d
                ✔ Délais rouges : %d

                ✔ Taux de respect global : %s
                """.formatted(
                contrat.getNomContrat(),
                LocalDate.now(),
                total,
                verts,
                jaunes,
                oranges,
                rouges,
                tauxRespect
        );

        Rapport rapport = new Rapport();
        rapport.setContenu(contenu.trim());
        rapport.setDateRapport(LocalDate.now());
        rapport.setContrat(contrat);

        return rapportRepository.save(rapport);
    }

    @Override
    public Rapport getRapportByContrat(Long contratId) {
        return (Rapport) rapportRepository.findByContratId(contratId)
                .orElseThrow(() -> new RuntimeException("Aucun rapport trouvé pour ce contrat."));
    }
}
