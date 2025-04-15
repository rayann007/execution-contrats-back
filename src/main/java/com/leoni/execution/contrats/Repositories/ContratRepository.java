package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.ContratContinue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface qui hérite de JpaRepository : toutes les opérations CRUD sont déjà prêtes
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
    // Tu peux ajouter des méthodes personnalisées ici (ex: findByType)
}

