package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.ContratContinue;
import com.leoni.execution.contrats.Models.typeService;
import com.leoni.execution.contrats.Repositories.ContratContinueRepository;
import com.leoni.execution.contrats.Services.ContratContinueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratContinueServiceImpl implements ContratContinueService {

    @Autowired
    private ContratContinueRepository contratContinueRepository;

    @Override
    public List<ContratContinue> getAll() {
        return contratContinueRepository.findAll();
    }

    @Override
    public ContratContinue getById(Long id) {
        return contratContinueRepository.findById(id).orElse(null);
    }

    public List<ContratContinue> getByTypeService(typeService type) {
        return contratContinueRepository.findByTypeService(type);
    }

    @Override
    public ContratContinue create(ContratContinue contratContinue) {
        return contratContinueRepository.save(contratContinue);
    }

    @Override
    public ContratContinue update(Long id, ContratContinue contratContinue) {
        Optional<ContratContinue> existing = contratContinueRepository.findById(id);
        if (existing.isPresent()) {
            contratContinue.setId(id); // Pour être sûr qu'on met à jour l'objet existant
            return contratContinueRepository.save(contratContinue);
        } else {
            return null; // Ou lancer une exception custom si besoin
        }
    }

    @Override
    public void delete(Long id) {
        contratContinueRepository.deleteById(id);
    }
}
