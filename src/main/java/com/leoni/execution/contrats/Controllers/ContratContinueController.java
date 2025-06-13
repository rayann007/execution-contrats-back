package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.ContratContinue;
import com.leoni.execution.contrats.Models.StatutContrat;
import com.leoni.execution.contrats.Models.typeService;
import com.leoni.execution.contrats.Services.ContratContinueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats-continue")
public class ContratContinueController {

    @Autowired
    private ContratContinueService contratContinueService;

    @GetMapping
    public List<ContratContinue> getAll() {
        return contratContinueService.getAll();
    }

    @GetMapping("/{id}")
    public ContratContinue getById(@PathVariable Long id) {
        return contratContinueService.getById(id);
    }



    @PostMapping
    public ContratContinue create(@RequestBody ContratContinue contratContinue) {
        return contratContinueService.create(contratContinue);
    }

    @PutMapping("/{id}")
    public ContratContinue update(@PathVariable Long id, @RequestBody ContratContinue contratContinue) {
        return contratContinueService.update(id, contratContinue);
    }

    @GetMapping("/type/{type}")
    public List<ContratContinue> getByType(@PathVariable("type") typeService type) {
        return contratContinueService.getByTypeService(type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contratContinueService.delete(id);
    }

    @PutMapping("/{id}/resilier")
    public ResponseEntity<String> resilierContrat(@PathVariable Long id) {
        ContratContinue contratContinue = contratContinueService.getById(id);

        if (contratContinue == null || contratContinue.getContrat() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrat non trouvé");
        }

        contratContinue.getContrat().setStatut(StatutContrat.résilié);
        contratContinueService.update(id,contratContinue); // ⚠️ Ou un update si tu en as un

        return ResponseEntity.ok("Contrat résilié avec succès");
    }

}
