package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.ContratTravaux;
import com.leoni.execution.contrats.Repositories.ContratTravauxRepository;
import com.leoni.execution.contrats.Services.ContratTravauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratTravauxServiceImpl implements ContratTravauxService {

    @Autowired
    private ContratTravauxRepository repository;

    @Override
    public List<ContratTravaux> getAll() {
        return repository.findAll();
    }

    @Override
    public ContratTravaux getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ContratTravaux create(ContratTravaux contratTravaux) {
        return repository.save(contratTravaux);
    }

    @Override
    public ContratTravaux update(Long id, ContratTravaux ct) {
        ContratTravaux existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setMontantAvance(ct.getMontantAvance());
            existing.setMontantFinal(ct.getMontantFinal());
            existing.setRetenueGarantie(ct.getRetenueGarantie());
            existing.setContrat(ct.getContrat());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
