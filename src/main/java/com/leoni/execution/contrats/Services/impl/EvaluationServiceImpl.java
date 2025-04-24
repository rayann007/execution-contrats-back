package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.DTO.EvaluationStatistiquesDTO;
import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.CouleurIndicateur;
import com.leoni.execution.contrats.Models.Evaluation;
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
        long jaunes = evaluations.stream().filter(e -> CouleurIndicateur.Jaune.equals(e.getCouleurIndicateur())).count();
        long oranges = evaluations.stream().filter(e -> CouleurIndicateur.Orange.equals(e.getCouleurIndicateur())).count();
        long rouges = evaluations.stream().filter(e -> CouleurIndicateur.Rouge.equals(e.getCouleurIndicateur())).count();

        EvaluationStatistiquesDTO dto = new EvaluationStatistiquesDTO();
        dto.setTotalDelais(total);
        dto.setDelaisVerts(verts);
        dto.setDelaisJaunes(jaunes);
        dto.setDelaisOranges(oranges);
        dto.setDelaisRouges(rouges);

        long respectes = verts; // On considère que Vert = respecté
        dto.setDelaisRespectes(respectes);
        dto.setDelaisNonRespectes(total - respectes);
        dto.setTauxRespect(total > 0 ? (respectes * 100 / total) + "%" : "0%");

        return dto;
    }

    private CouleurIndicateur calculerCouleur(Contrat contrat) {
        if (contrat == null || contrat.getDateDebut() == null || contrat.getDateFin() == null) {
            return CouleurIndicateur.Gris;
        }

        LocalDate today = LocalDate.now();
        LocalDate debut = contrat.getDateDebut();
        LocalDate fin = contrat.getDateFin();

        if (today.isAfter(fin)) return CouleurIndicateur.Rouge;

        long total = ChronoUnit.DAYS.between(debut, fin);
        long restant = ChronoUnit.DAYS.between(today, fin);

        if (total <= 0) return CouleurIndicateur.Gris;

        double ratio = (double) restant / total;

        if (ratio > 0.5) return CouleurIndicateur.Vert;
        if (ratio > 0.2) return CouleurIndicateur.Jaune;
        return CouleurIndicateur.Orange;
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

}
