package com.leoni.execution.contrats.Controllers;

import com.leoni.execution.contrats.Models.Utilisateur;
import com.leoni.execution.contrats.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // ✅ Ajouter un nouvel utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> ajouter(@RequestBody Utilisateur utilisateur) {
        Utilisateur saved = utilisateurService.ajouter(utilisateur);
        return ResponseEntity.ok(saved);
    }

    // ✅ Lister tous les utilisateurs
    @GetMapping
    public List<Utilisateur> listerTous() {
        return utilisateurService.listerTous();
    }

    // ✅ Filtrer les utilisateurs par rôle (admin / user)
    @GetMapping("/role/{role}")
    public List<Utilisateur> parRole(@PathVariable Utilisateur.RoleName role) {
        return utilisateurService.listerParRole(role);
    }

    // ✅ Supprimer un utilisateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        utilisateurService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
