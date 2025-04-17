package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.DelaiContractuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DelaiContractuelRepository extends JpaRepository<DelaiContractuel, Long> {
    List<DelaiContractuel> findByContratId(Long contratId);
}
