package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.DTO.EvaluationStatistiquesDTO;
import com.leoni.execution.contrats.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * Obtenir les statistiques globales pour un contrat donné
     */
    @GetMapping("/statistiques/{contratId}")
    public EvaluationStatistiquesDTO getStatistiquesPourContrat(@PathVariable Long contratId) {
        return evaluationService.genererStatistiquesContrat(contratId);
    }
}
