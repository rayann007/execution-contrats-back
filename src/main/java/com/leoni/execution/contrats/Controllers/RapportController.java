package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Rapport;
import com.leoni.execution.contrats.Services.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rapports")
public class RapportController {

    @Autowired
    private RapportService rapportService;

    @PostMapping("/generer/{contratId}")
    public Rapport genererRapport(@PathVariable Long contratId) {
        return rapportService.genererRapportPourContrat(contratId);
    }

    @GetMapping("/voir/{contratId}")
    public Rapport getRapport(@PathVariable Long contratId) {
        return rapportService.getRapportByContrat(contratId);
    }
}
