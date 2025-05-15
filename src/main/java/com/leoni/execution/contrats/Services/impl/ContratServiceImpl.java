package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.*;
import com.leoni.execution.contrats.Repositories.ContratRepository;
import com.leoni.execution.contrats.Repositories.DocumentRepository;
import com.leoni.execution.contrats.Services.ArchivageService;
import com.leoni.execution.contrats.Services.ContratService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
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


    @Override
    public List<Contrat> getContratsActifsAujourdHui() {
        return contratRepository.findContratsActifsAujourdHui(LocalDate.now());
    }
    public long countContratsActifsAujourdHui() {
        return contratRepository.countByDateDebutBeforeAndDateFinAfter(LocalDate.now(), LocalDate.now());
    }


    @Override
    public List<Contrat> getContratsEnAlerte() {
        LocalDate today = LocalDate.now();
        List<Contrat> alertes = new ArrayList<>();

        for (Contrat c : contratRepository.findAll()) {
            if (c.getType() == TypeContrat.Travaux) {
                // Alerte si date de fin proche
                if (c.getDateFin() != null && !c.getDateFin().isBefore(today) && c.getDateFin().isBefore(today.plusDays(7))) {
                    alertes.add(c);
                    continue;
                }

                // Alerte si un délai approche
                List<DelaiContractuel> delais = c.getDelais(); // Assure-toi que le getter est bien fait
                if (delais != null) {
                    for (DelaiContractuel d : delais) {
                        if (!d.getDate().isBefore(today) && d.getDate().isBefore(today.plusDays(7))) {
                            alertes.add(c);
                            break;
                        }
                    }
                }

            } else if (c.getType() == TypeContrat.Continu) {
                // Alerte 2 mois avant la fin
                if (c.getDateFin() != null && !c.getDateFin().isBefore(today) && c.getDateFin().isBefore(today.plusMonths(2))) {
                    alertes.add(c);
                }
            }
        }

        return alertes;
    }

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ArchivageService archivageService;

    private void verifierEtArchiverContrat(Contrat contrat) {
        if (contrat.getStatut().equals(StatutContrat.résilié) ||
                contrat.getStatut().equals(StatutContrat.terminé)) {

            List<Document> docs = documentRepository.findByContrat_Id(contrat.getId());
            archivageService.archiverDocumentsContrat(docs);
        }


    }

    @Override
    public void resilierContratEtArchiver(Long id) {
        Optional<Contrat> optionalContrat = contratRepository.findById(id);

        if (optionalContrat.isPresent()) {
            Contrat contrat = optionalContrat.get();

            // Mise à jour du statut
            contrat.setStatut(StatutContrat.résilié);
            contratRepository.save(contrat);

            // Récupération des documents
            List<Document> docs = documentRepository.findByContrat_Id(id);

            // Archivage
            archivageService.archiverDocumentsContrat(docs);
        } else {
            throw new RuntimeException("Contrat avec ID " + id + " non trouvé.");
        }


    }

    @Override
    public List<Contrat> getEcheancesDuMois() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate startOfMonth = currentMonth.atDay(1);
        LocalDate endOfMonth = currentMonth.atEndOfMonth();

        return contratRepository.findContratsByDateFinBetween(startOfMonth, endOfMonth);
    }
}