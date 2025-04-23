package com.leoni.execution.contrats.Services.impl;

import com.leoni.execution.contrats.Models.Personnel;
import com.leoni.execution.contrats.Models.ServiceType;
import com.leoni.execution.contrats.Repositories.PersonnelRepository;
import com.leoni.execution.contrats.Services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public List<Personnel> getAll() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel getById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    @Override
    public Personnel create(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public Personnel update(Long id, Personnel personnel) {
        personnel.setId(id);
        return personnelRepository.save(personnel);
    }

    @Override
    public void delete(Long id) {
        personnelRepository.deleteById(id);
    }

    @Override
    public List<Personnel> getByService(String service) {
        return personnelRepository.findByService(ServiceType.valueOf(service));
    }

    @Override
    public List<Personnel> searchByNomOrFonction(String keyword) {
        return personnelRepository.searchByNomOrFonction(keyword);
    }

    @Override
    public List<Personnel> getByServiceSortedByNom(String service) {
        return personnelRepository.findByServiceOrderByNomAsc(ServiceType.valueOf(service));
    }

}
