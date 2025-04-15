package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.ContratContinue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratContinueRepository extends JpaRepository<ContratContinue, Long> {
}
