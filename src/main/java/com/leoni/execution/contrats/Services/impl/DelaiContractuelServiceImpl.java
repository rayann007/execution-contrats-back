package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.DelaiContractuel;
import com.leoni.execution.contrats.Repositories.DelaiContractuelRepository;
import com.leoni.execution.contrats.Services.DelaiContractuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelaiContractuelServiceImpl implements DelaiContractuelService {

    @Autowired
    private DelaiContractuelRepository repository;

    @Override
    public DelaiContractuel create(DelaiContractuel delai) {
        return repository.save(delai);
    }

    @Override
    public List<DelaiContractuel> getByContrat(Long contratId) {
        return repository.findByContratId(contratId);
    }
}
