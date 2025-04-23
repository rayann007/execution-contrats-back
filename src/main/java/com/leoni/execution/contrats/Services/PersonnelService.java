package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.Personnel;

import java.util.List;

public interface PersonnelService {
    List<Personnel> getAll();
    Personnel getById(Long id);
    Personnel create(Personnel personnel);
    Personnel update(Long id, Personnel personnel);
    void delete(Long id);
    List<Personnel> getByService(String service);

    List<Personnel> searchByNomOrFonction(String keyword);
    List<Personnel> getByServiceSortedByNom(String service);

}
