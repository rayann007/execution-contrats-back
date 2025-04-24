package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Utilisateur;
import com.leoni.execution.contrats.Repositories.UtilisateurRepository;
import com.leoni.execution.contrats.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

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
}
