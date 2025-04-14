package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.Contrat;

import java.util.List;

public interface ContratService {

    List<Contrat> getAllContrats();             // Lire tous les contrats
    Contrat getContratById(Long id);            // Lire un contrat spécifique
    Contrat createContrat(Contrat contrat);     // Créer un nouveau contrat
    Contrat updateContrat(Long id, Contrat c);  // Mettre à jour un contrat existant
    void deleteContrat(Long id);                // Supprimer un contrat
}
