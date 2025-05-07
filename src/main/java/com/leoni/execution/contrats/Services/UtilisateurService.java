
package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur findByEmail(String email);
    Utilisateur ajouter(Utilisateur utilisateur);
    List<Utilisateur> listerTous();
    List<Utilisateur> listerParRole(Utilisateur.RoleName role);
    void supprimer(Long id);
}
