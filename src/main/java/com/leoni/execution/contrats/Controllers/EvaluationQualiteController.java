package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.EvaluationQualite;
import com.leoni.execution.contrats.Models.EvaluationQualiteDTO;
import com.leoni.execution.contrats.Models.ServiceType;
import com.leoni.execution.contrats.Services.EvaluationQualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationQualiteController {

    @Autowired
    private EvaluationQualiteService evaluationService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("service") ServiceType service,
            @RequestParam("remarque") String remarque,
            @RequestParam("file") MultipartFile file,
            @RequestParam("utilisateurId") Long utilisateurId) {

        try {
            evaluationService.enregistrer(service, remarque, file, utilisateurId);
            return ResponseEntity.ok("Évaluation enregistrée !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Erreur : " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @GetMapping("/service/{service}")
    public List<EvaluationQualite> getByService(@PathVariable ServiceType service) {
        return evaluationService.getByService(service);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        byte[] content = evaluationService.download(id);
        if (content == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=evaluation_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }

    @GetMapping("/utilisateur/{id}")
    public List<EvaluationQualiteDTO> getByUtilisateur(@PathVariable Long id) {
        return evaluationService.getEvaluationsByUtilisateur(id);
    }

}
