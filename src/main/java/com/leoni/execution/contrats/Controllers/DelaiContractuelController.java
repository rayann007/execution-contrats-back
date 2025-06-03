package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.DelaiContractuel;
import com.leoni.execution.contrats.Services.DelaiContractuelService;
import com.leoni.execution.contrats.dto.DelaiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/delais")
public class DelaiContractuelController {

    @Autowired
    private DelaiContractuelService service;

    @PostMapping
    public DelaiContractuel create(@RequestBody DelaiRequest req) {
        DelaiContractuel delai = new DelaiContractuel();

        delai.setDate(LocalDate.parse(req.date));
        delai.setJoursRetard(req.joursRetard);
        delai.setMontantPenaliteJournalier(req.montantPenaliteJournalier);
        delai.setPenalitePayee(req.penalitePayee);
        delai.setCommentaire(req.commentaire);

        // Associer le contrat manuellement
        Contrat contrat = new Contrat();
        contrat.setId(req.contrat_id); // ✅ référence partielle suffisante pour Hibernate
        delai.setContrat(contrat);

        return service.create(delai);
    }

    @GetMapping("/contrat/{id}")
    public List<DelaiContractuel> getByContrat(@PathVariable Long id) {
        return service.getByContrat(id);
    }


}
