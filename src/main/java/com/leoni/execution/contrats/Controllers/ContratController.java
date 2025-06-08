package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.StatutContrat;
import com.leoni.execution.contrats.Models.TypeContrat;
import com.leoni.execution.contrats.Services.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
    @GetMapping("/pourcentage-par-type")
    public Map<String, String> getPourcentageParType() {
        List<Contrat> tous = Optional.ofNullable(contratService.getAllContrats())
                .orElse(Collections.emptyList());

        long total = tous.size();

        Map<String, String> pourcentages = new HashMap<>();

        if (total > 0) {
            long travaux = tous.stream().filter(c -> c.getType() == TypeContrat.Travaux).count();
            long prestation = tous.stream().filter(c -> c.getType() == TypeContrat.Prestation).count();
            long continus = tous.stream().filter(c -> c.getType() == TypeContrat.Continu).count();

            // ✅ Calculs arrondis
            int pctTravaux = (int) Math.round((travaux * 100.0) / total);
            int pctPrestation = (int) Math.round((prestation * 100.0) / total);
            int pctContinus = 100 - pctTravaux - pctPrestation; // Ajustement final

            // ✅ Ajout des résultats
            pourcentages.put("travaux", pctTravaux + "%");
            pourcentages.put("prestation", pctPrestation + "%");
            pourcentages.put("continue", pctContinus + "%");
        } else {
            pourcentages.put("travaux", "0%");
            pourcentages.put("prestation", "0%");
            pourcentages.put("continue", "0%");
        }

        return pourcentages;
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
    @GetMapping("/count-actifs-aujourdhui")
    public long countContratsActifsAujourdHui() {
        return contratService.countContratsActifsAujourdHui();
    }

    @GetMapping("/alertes")
    public List<Contrat> getContratsEnAlerte() {
        return contratService.getContratsEnAlerte();
    }
    @GetMapping("/count-alertes")
    public long countContratsEnAlerte() {
        return contratService.getContratsEnAlerte().size();
    }


    @PutMapping("/resilier-et-archiver/{id}")
    public ResponseEntity<String> resilierEtArchiver(@PathVariable Long id) {
        try {
            contratService.resilierContratEtArchiver(id);
            return ResponseEntity.ok("Contrat résilié et documents archivés avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/count-continus-alertes")
    public long countContratsContinusEnAlerte() {
        return contratService.countContratsContinusEnAlerte();
    }


    @GetMapping("/echeances-mois")
    public ResponseEntity<?> getEcheancesDuMois() {
        List<Contrat> contrats = contratService.getEcheancesDuMois();

        if (contrats.isEmpty()) {
            return ResponseEntity.ok("Aucune échéance ce mois");
        } else {
            return ResponseEntity.ok(contrats);
        }
    }
    @GetMapping("/echeances-mois-continus")
    public ResponseEntity<?> getContratsContinusEcheanceMois() {
        List<Contrat> contrats = contratService.getContratsContinusAvecEcheanceCeMois();

        if (contrats.isEmpty()) {
            return ResponseEntity.ok("Aucun contrat continu avec échéance ce mois");
        } else {
            return ResponseEntity.ok(contrats);
        }
    }



}









