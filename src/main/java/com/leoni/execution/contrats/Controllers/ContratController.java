package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Services.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indique que cette classe est un contrôleur REST (pas une vue HTML)
@RequestMapping("/api/contrats") // Tous les endpoints commenceront par /api/contrats
public class ContratController {

    @Autowired // Permet à Spring d'injecter automatiquement une instance de ContratService
    private ContratService contratService;

    // ======= METHODES CRUD =======

    // Récupérer tous les contrats
    @GetMapping
    public List<Contrat> getAllContrats() {
        // Appelle le service pour obtenir la liste des contrats depuis la base de données
        return contratService.getAllContrats();
    }

    // Récupérer un contrat par ID
    @GetMapping("/{id}")
    public Contrat getContratById(@PathVariable Long id) {
        // Le @PathVariable récupère l'ID depuis l'URL (/api/contrats/5 par exemple)
        return contratService.getContratById(id);
    }

    // Créer un nouveau contrat
    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        // Le @RequestBody permet de convertir automatiquement un JSON en objet Contrat
        return contratService.createContrat(contrat);
    }

    // Mettre à jour un contrat existant
    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
        // Combine ID depuis l'URL et les nouvelles données du corps pour faire la mise à jour
        return contratService.updateContrat(id, contrat);
    }

    // Supprimer un contrat
    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable Long id) {
        // Supprime le contrat correspondant à l'ID
        contratService.deleteContrat(id);
    }
}
