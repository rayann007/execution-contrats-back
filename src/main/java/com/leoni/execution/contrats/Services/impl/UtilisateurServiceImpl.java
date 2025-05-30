package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Utilisateur;
import com.leoni.execution.contrats.Repositories.UtilisateurRepository;
import com.leoni.execution.contrats.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur ajouter(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> listerTous() {
        return utilisateurRepository.findAll();
    }

    @Override
    public List<Utilisateur> listerParRole(Utilisateur.RoleName role) {
        return utilisateurRepository.findByRole(role);
    }

    @Override
    public void supprimer(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        Optional<Utilisateur> existant = utilisateurRepository.findById(utilisateur.getId());
        if (existant.isPresent()) {
            Utilisateur u = existant.get();
            u.setEmail(utilisateur.getEmail());
            u.setNom(utilisateur.getNom());
            u.setServiceAffectation(utilisateur.getServiceAffectation());
            u.setRole(utilisateur.getRole());
            return utilisateurRepository.save(u);
        }
        throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + utilisateur.getId());
    }

    @Override
    public void reinitialiserMotDePasse(Long id) {
        utilisateurRepository.findById(id).ifPresent(u -> {
            u.setMotDePasse(passwordEncoder.encode("123456")); // ou un mot aléatoire si tu veux aller plus loin
            utilisateurRepository.save(u);
        });
    }
}
