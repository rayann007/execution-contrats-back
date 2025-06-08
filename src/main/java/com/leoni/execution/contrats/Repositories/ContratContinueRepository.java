package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.ContratContinue;
import com.leoni.execution.contrats.Models.typeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratContinueRepository extends JpaRepository<ContratContinue, Long> {
    List<ContratContinue> findByTypeService(typeService typeService);
}
