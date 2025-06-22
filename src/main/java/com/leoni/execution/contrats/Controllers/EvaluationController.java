package com.leoni.execution.contrats.Controllers;


import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.CouleurIndicateur;
import com.leoni.execution.contrats.Models.Evaluation;
import com.leoni.execution.contrats.Models.EvaluationStatistiquesDTO;
import com.leoni.execution.contrats.Services.EvaluationService;
import com.leoni.execution.contrats.dto.EvaluationDelaisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/evaluer-delais")
    public ResponseEntity<Evaluation> evaluerDelais(@RequestBody EvaluationDelaisDTO dto) {
        System.out.println("✅ Accès autorisé !");
        int total = dto.getDelaisRespectes().size();
        int respectes = (int) dto.getDelaisRespectes().stream().filter(b -> b).count();

        double taux = total == 0 ? 0 : (double) respectes / total;

        CouleurIndicateur couleur;
        if (taux >= 0.7) couleur = CouleurIndicateur.Vert;
        else if (taux >= 0.5) couleur = CouleurIndicateur.Jaune;
        else if (taux >= 0.3) couleur = CouleurIndicateur.Orange;
        else couleur = CouleurIndicateur.Rouge;

        Evaluation evaluation = new Evaluation();
        evaluation.setContrat(new Contrat(dto.getContratId())); // ou charger le vrai Contrat si besoin
        evaluation.setDateEvaluation(LocalDate.now());
        evaluation.setCouleurIndicateur(couleur);
        evaluation.setCommentaire("Évaluation générée via délai respecté");

        return ResponseEntity.ok(evaluationService.saveEvaluation(evaluation));
    }

    @GetMapping("/contrat/{contratId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByContrat(@PathVariable Long contratId) {
        List<Evaluation> evaluations = evaluationService.findByContratId(contratId);
        return ResponseEntity.ok(evaluations);
    }


}
