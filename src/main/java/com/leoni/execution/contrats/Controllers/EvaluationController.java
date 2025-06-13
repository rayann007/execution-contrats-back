package com.leoni.execution.contrats.Controllers;


import com.leoni.execution.contrats.Models.CouleurIndicateur;
import com.leoni.execution.contrats.Models.Evaluation;
import com.leoni.execution.contrats.Models.EvaluationStatistiquesDTO;
import com.leoni.execution.contrats.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping
    public ResponseEntity<Evaluation> ajouterEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation saved = evaluationService.saveEvaluation(evaluation); // 💡 la couleur est calculée ici
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/couleur/{couleur}")
    public ResponseEntity<List<Evaluation>> getByCouleur(@PathVariable CouleurIndicateur couleur) {
        List<Evaluation> evaluations = evaluationService.findByCouleur(couleur);
        return ResponseEntity.ok(evaluations);
    }

}
