package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.ContratTravaux;
import java.util.List;

public interface ContratTravauxService {
    List<ContratTravaux> getAll();
    ContratTravaux getById(Long id);
    ContratTravaux create(ContratTravaux contratTravaux);
    ContratTravaux update(Long id, ContratTravaux contratTravaux);
    void delete(Long id);
}
