package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.EvaluationQualite;
import com.leoni.execution.contrats.Models.EvaluationQualiteDTO;
import com.leoni.execution.contrats.Models.ServiceType;
import com.leoni.execution.contrats.Models.Utilisateur;
import com.leoni.execution.contrats.Repositories.EvaluationQualiteRepository;
import com.leoni.execution.contrats.Repositories.UtilisateurRepository;
import com.leoni.execution.contrats.Services.EvaluationQualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.leoni.execution.contrats.Repositories.UtilisateurRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EvaluationQualiteServiceImpl implements EvaluationQualiteService {

    @Autowired
    private EvaluationQualiteRepository repository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void enregistrer(ServiceType service, String remarque, MultipartFile fichier, Long utilisateurId) throws IOException {
        EvaluationQualite evaluation = new EvaluationQualite();
        evaluation.setService(service);
        evaluation.setRemarque(remarque);
        evaluation.setNomFichier(fichier.getOriginalFilename());
        evaluation.setDate(LocalDate.now());
        evaluation.setFichier(fichier.getBytes());

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow();
        evaluation.setUtilisateur(utilisateur);

        repository.save(evaluation);
    }

    @Override
    public List<EvaluationQualite> getByService(ServiceType service) {
        return repository.findByService(service);
    }

    @Override
    public byte[] download(Long id) {
        return repository.findById(id).map(EvaluationQualite::getFichier).orElse(null);
    }

    @Override
    public List<EvaluationQualiteDTO> getEvaluationsByUtilisateur(Long utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId).stream()
                .map(eval -> new EvaluationQualiteDTO(
                        eval.getService().name(),
                        eval.getRemarque(),
                        eval.getNomFichier(),
                        eval.getUtilisateur().getNom()
                ))
                .toList();
    }

}
