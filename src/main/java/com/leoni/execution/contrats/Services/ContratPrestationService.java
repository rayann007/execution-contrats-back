package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.ContratPrestation;

import java.util.List;

public interface ContratPrestationService {
    List<ContratPrestation> getAll();
    ContratPrestation getById(Long id);
    ContratPrestation create(ContratPrestation contratPrestation);
    void delete(Long id);
}
