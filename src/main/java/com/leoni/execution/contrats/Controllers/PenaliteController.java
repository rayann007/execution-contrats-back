package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.DelaiContractuel;
import com.leoni.execution.contrats.Repositories.DelaiContractuelRepository;
import com.leoni.execution.contrats.Services.PenaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalites")
public class PenaliteController {

    @Autowired
    private PenaliteService penaliteService;

    @Autowired
    private DelaiContractuelRepository delaiContractuelRepository;

    /**
     * 🔁 Déclenche le recalcul automatique des pénalités
     */
    @PostMapping("/calculer")
    public String recalculerPenalites() {
        penaliteService.calculerPenaliteAutomatiquement();
        return "✅ Recalcul des pénalités effectué.";
    }

    /**
     * 📄 Récupère tous les délais en retard non payés
     */
    @GetMapping("/retards")
    public List<DelaiContractuel> getDelaisNonPayes() {
        return delaiContractuelRepository.findByPenalitePayeeFalse();
    }

    /**
     * ✅ Marquer une pénalité comme payée
     */
    @PutMapping("/payer/{id}")
    public String marquerCommePayee(@PathVariable Long id) {
        DelaiContractuel delai = delaiContractuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Délai introuvable"));

        delai.setPenalitePayee(true);
        delaiContractuelRepository.save(delai);

        return "✅ Pénalité marquée comme payée.";
    }


    @RequestMapping("/api/penalites")

        // ✔️ Endpoint pour lister tous les retards avec pénalités
        @GetMapping("/retards")
        public List<DelaiContractuel> getRetardsAvecPenalites() {
            return delaiContractuelRepository.findAll();
        }

    @RestController
    @RequestMapping("/api/delais")
    public class DelaiContractuelController {

        @Autowired
        private DelaiContractuelRepository delaiContractuelRepository;

        // ✅ Lister tous les délais contractuels
        @GetMapping
        public List<DelaiContractuel> getAllDelais() {
            return delaiContractuelRepository.findAll();
        }
    }



    }



