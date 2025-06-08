package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.StatutContrat;
import com.leoni.execution.contrats.Models.TypeContrat;

import java.time.LocalDate;
import java.util.List;

public interface ContratService {

    List<Contrat> getAllContrats();             // Lire tous les contrats
    Contrat getContratById(Long id);            // Lire un contrat spécifique
    Contrat createContrat(Contrat contrat);     // Créer un nouveau contrat
    Contrat updateContrat(Long id, Contrat c);  // Mettre à jour un contrat existant
    void deleteContrat(Long id);                // Supprimer un contrat

    List<Contrat> searchByNom(String nomContrat);

    List<Contrat> findByDateDebutAfter(LocalDate dateDebut);

    List<Contrat> findByDateFinBefore(LocalDate dateFin);

    List<Contrat> findByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);

    List<Contrat> findByType(TypeContrat type);
    List<Contrat> findByStatut(StatutContrat statut);
    // 🔎 Moteur de recherche global (par mot-clé)
    List<Contrat> filtrerContrats(TypeContrat type, StatutContrat statut, LocalDate dateDebut, LocalDate dateFin, String nom);

    List<Contrat> getContratsActifsAujourdHui();
    long countContratsActifsAujourdHui();


    List<Contrat> getContratsEnAlerte();




    void resilierContratEtArchiver(Long id);

    List<Contrat> getEcheancesDuMois();


    long countContratsContinusEnAlerte();

    List<Contrat> getContratsContinusAvecEcheanceCeMois();
}
