package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.StatutContrat;
import com.leoni.execution.contrats.Models.TypeContrat;
import com.leoni.execution.contrats.Services.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/search")
    public List<Contrat> searchContrats(@RequestParam String nom) {
        return contratService.searchByNom(nom);
    }

    // ✅ Endpoint pour filtrer les contrats par dateDebut et/ou dateFin
    @GetMapping("/filter-by-date")
    public List<Contrat> filterByDate(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {

        // ⚙️ Si les deux sont présents : filtrer entre deux dates
        if (dateDebut != null && dateFin != null) {
            return contratService.findByDateDebutAndDateFin(dateDebut, dateFin);
        }
        // ⚙️ Si seulement dateDebut est présent
        else if (dateDebut != null) {
            return contratService.findByDateDebutAfter(dateDebut);
        }
        // ⚙️ Si seulement dateFin est présent
        else if (dateFin != null) {
            return contratService.findByDateFinBefore(dateFin);
        }
        // 🔁 Sinon retourner tous les contrats
        else {
            return contratService.getAllContrats();
        }
    }

    @GetMapping("/filter-by-type")
    public List<Contrat> filterByType(@RequestParam TypeContrat type) {
        return contratService.findByType(type);
    }

    @GetMapping("/filter-by-statut")
    public List<Contrat> filterByStatut(@RequestParam StatutContrat statut) {
        return contratService.findByStatut(statut);
    }

    // ✅ Filtrage par type, statut, date, nom
    @GetMapping("/filtrer")
    public List<Contrat> filtrerContrats(
            @RequestParam(required = false) TypeContrat type,
            @RequestParam(required = false) StatutContrat statut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            @RequestParam(required = false) String nom
    ) {
        return contratService.filtrerContrats(type, statut, dateDebut, dateFin, nom);
    }

    @GetMapping("/actifs-aujourdhui")
    public List<Contrat> getContratsActifsAujourdHui() {
        return contratService.getContratsActifsAujourdHui();
    }

    @GetMapping("/alertes")
    public List<Contrat> getContratsEnAlerte() {
        return contratService.getContratsEnAlerte();
    }

}









