package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.StatutContrat;
import com.leoni.execution.contrats.Models.TypeContrat;
import com.leoni.execution.contrats.Repositories.ContratRepository;
import com.leoni.execution.contrats.Services.ContratService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContratServiceImpl implements ContratService {

    @Autowired
    private ContratRepository contratRepository;

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable avec l’ID : " + id));
    }

    @Override
    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContrat(Long id, Contrat updatedContrat) {
        Contrat existing = contratRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat à modifier introuvable avec l’ID : " + id));

        // Mise à jour des champs
        existing.setNomContrat(updatedContrat.getNomContrat());
        existing.setType(updatedContrat.getType());
        existing.setDateDebut(updatedContrat.getDateDebut());
        existing.setDateFin(updatedContrat.getDateFin());
        existing.setStatut(updatedContrat.getStatut());
        existing.setServiceConcerne(updatedContrat.getServiceConcerne());
        existing.setResponsableLeoni(updatedContrat.getResponsableLeoni());
        existing.setEmailResponsable(updatedContrat.getEmailResponsable());
        existing.setEmailsPersonnesDediees(updatedContrat.getEmailsPersonnesDediees());

        return contratRepository.save(existing);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }


    @Override
    public List<Contrat> searchByNom(String nomContrat) {
        return contratRepository.findByNomContratContainingIgnoreCase(nomContrat);
    }

    @Override
    public List<Contrat> findByDateDebutAfter(LocalDate dateDebut) {
        // 🔁 Récupère tous les contrats qui commencent après la date donnée
        return contratRepository.findByDateDebutAfter(dateDebut);
    }

    @Override
    public List<Contrat> findByDateFinBefore(LocalDate dateFin) {
        // 🔁 Récupère tous les contrats qui se terminent avant la date donnée
        return contratRepository.findByDateFinBefore(dateFin);
    }

    @Override
    public List<Contrat> findByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin) {
        // 🔁 Récupère les contrats entre deux dates
        return contratRepository.findByDateDebutAfterAndDateFinBefore(dateDebut, dateFin);
    }
    @Override
    public List<Contrat> findByType(TypeContrat type) {
        return contratRepository.findByType(type);
    }

    @Override
    public List<Contrat> findByStatut(StatutContrat statut) {
        return contratRepository.findByStatut(statut);
    }
    @Override
    public List<Contrat> filtrerContrats(TypeContrat type, StatutContrat statut, LocalDate dateDebut, LocalDate dateFin, String nom) {
        return contratRepository.filtrerContrats(type, statut, dateDebut, dateFin, nom);
    }














}
