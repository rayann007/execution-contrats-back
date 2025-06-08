package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.ContratContinue;
import com.leoni.execution.contrats.Models.typeService;

import java.util.List;

public interface ContratContinueService {
    List<ContratContinue> getAll();
    ContratContinue getById(Long id);
    ContratContinue create(ContratContinue contratContinue);
    ContratContinue update(Long id, ContratContinue contratContinue);
    void delete(Long id);

    List<ContratContinue> getByTypeService(typeService type);
}
