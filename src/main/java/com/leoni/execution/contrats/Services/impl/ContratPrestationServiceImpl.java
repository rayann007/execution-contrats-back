package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Contrat;
import com.leoni.execution.contrats.Models.ContratPrestation;
import com.leoni.execution.contrats.Repositories.ContratPrestationRepository;
import com.leoni.execution.contrats.Repositories.ContratRepository;
import com.leoni.execution.contrats.Services.ContratPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratPrestationServiceImpl implements ContratPrestationService {

    @Autowired
    private ContratPrestationRepository repository;

    @Autowired
    private ContratRepository contratRepository;

    @Override
    public List<ContratPrestation> getAll() {
        return repository.findAll();
    }

    @Override
    public ContratPrestation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ContratPrestation create(ContratPrestation contratPrestation) {
        // Récupérer le contrat attaché à la session Hibernate
        Long contratId = contratPrestation.getContrat().getId();
        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable avec ID: " + contratId));

        // Attacher le contrat managé à la prestation
        contratPrestation.setContrat(contrat);

        return repository.save(contratPrestation);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
