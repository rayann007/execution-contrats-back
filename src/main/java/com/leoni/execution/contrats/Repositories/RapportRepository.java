package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
    Optional<Object> findByContratId(Long contratId);

    boolean existsByContratId(Long contratId);
}
