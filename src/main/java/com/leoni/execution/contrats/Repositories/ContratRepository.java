package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.StatutContrat;
import com.leoni.execution.contrats.Models.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Interface qui hérite de JpaRepository : toutes les opérations CRUD sont déjà prêtes
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
    // Tu peux ajouter des méthodes personnalisées ici (ex: findByType)
    List<Contrat> findByNomContratContainingIgnoreCase(String nomContrat); // findByName

        // 🔍 Contrats dont la date de début est après une certaine date
        List<Contrat> findByDateDebutAfter(LocalDate dateDebut);

        // 🔍 Contrats dont la date de fin est avant une certaine date
        List<Contrat> findByDateFinBefore(LocalDate dateFin);

        // 🔍 Contrats entre deux dates
        List<Contrat> findByDateDebutAfterAndDateFinBefore(LocalDate dateDebut, LocalDate dateFin);

         List<Contrat> findByType(TypeContrat type);
    List<Contrat> findByStatut(StatutContrat statut);

    @Query("SELECT c FROM Contrat c WHERE " +
            "(:type IS NULL OR c.type = :type) AND " +
            "(:statut IS NULL OR c.statut = :statut) AND " +
            "(:dateDebut IS NULL OR c.dateDebut >= :dateDebut) AND " +
            "(:dateFin IS NULL OR c.dateFin <= :dateFin) AND " +
            "(:nom IS NULL OR LOWER(c.nomContrat) LIKE LOWER(CONCAT(:nom, '%')))")
    List<Contrat> filtrerContrats(
            @Param("type") TypeContrat type,
            @Param("statut") StatutContrat statut,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin,
            @Param("nom") String nom
    );














}




