package com.leoni.execution.contrats.Services.impl;


import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.CouleurIndicateur;
import com.leoni.execution.contrats.Models.Evaluation;
import com.leoni.execution.contrats.Models.EvaluationStatistiquesDTO;
import com.leoni.execution.contrats.Repositories.EvaluationRepository;
import com.leoni.execution.contrats.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public EvaluationStatistiquesDTO genererStatistiquesContrat(Long contratId) {
        List<Evaluation> evaluations = evaluationRepository.findByContratId(contratId);
        long total = evaluations.size();

        long verts = evaluations.stream().filter(e -> CouleurIndicateur.Vert.equals(e.getCouleurIndicateur())).count();
        long oranges = evaluations.stream().filter(e -> CouleurIndicateur.Orange.equals(e.getCouleurIndicateur())).count();
        long rouges = evaluations.stream().filter(e -> CouleurIndicateur.Rouge.equals(e.getCouleurIndicateur())).count();


        EvaluationStatistiquesDTO dto = new EvaluationStatistiquesDTO();
        dto.setTotalDelais(total);
        dto.setDelaisVerts(verts);
        dto.setDelaisOranges(oranges);
        dto.setDelaisRouges(rouges);


        long respectes = verts; // Vert uniquement = respecté
        dto.setDelaisRespectes(respectes);
        dto.setDelaisNonRespectes(total - respectes);

        double taux = (total > 0) ? ((double) respectes * 100 / total) : 0;
        dto.setTauxRespect(String.format("%.2f%%", taux));

        return dto;
    }

    private CouleurIndicateur calculerCouleur(Contrat contrat) {
        if (contrat == null || contrat.getDateDebut() == null || contrat.getDateFin() == null) {
            return CouleurIndicateur.Vert;
        }

        LocalDate today = LocalDate.now();
        LocalDate debut = contrat.getDateDebut();
        LocalDate fin = contrat.getDateFin();



        long total = ChronoUnit.DAYS.between(debut, fin);
        long restant = ChronoUnit.DAYS.between(today, fin);



        double ratio = (double) restant / total;

        if (ratio > 0.7) return CouleurIndicateur.Vert;
        if (ratio > 0.3) return CouleurIndicateur.Orange;
        return CouleurIndicateur.Rouge;
    }

    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        evaluation.setCouleurIndicateur(calculerCouleur(evaluation.getContrat()));
        return evaluationRepository.save(evaluation);
    }

    @Override
    public List<Evaluation> findByCouleur(CouleurIndicateur couleur) {
        return evaluationRepository.findByCouleurIndicateur(couleur);
    }
    public List<Evaluation> findByContratId(Long contratId) {
        return evaluationRepository.findByContratId(contratId);
    }

}
