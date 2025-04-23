package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.DTO.EvaluationStatistiquesDTO;
import com.leoni.execution.contrats.Models.Evaluation;
import com.leoni.execution.contrats.Repositories.EvaluationRepository;
import com.leoni.execution.contrats.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public EvaluationStatistiquesDTO genererStatistiquesContrat(Long contratId) {
        List<Evaluation> evaluations = evaluationRepository.findByContratId(contratId);
        long total = evaluations.size();

        long verts = evaluations.stream().filter(e -> "Vert".equals(e.getCouleurIndicateur())).count();
        long jaunes = evaluations.stream().filter(e -> "Jaune".equals(e.getCouleurIndicateur())).count();
        long oranges = evaluations.stream().filter(e -> "Orange".equals(e.getCouleurIndicateur())).count();
        long rouges = evaluations.stream().filter(e -> "Rouge".equals(e.getCouleurIndicateur())).count();

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
}
