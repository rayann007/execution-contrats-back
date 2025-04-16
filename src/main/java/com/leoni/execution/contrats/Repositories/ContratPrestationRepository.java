package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.ContratPrestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratPrestationRepository extends JpaRepository<ContratPrestation, Long> {
}
