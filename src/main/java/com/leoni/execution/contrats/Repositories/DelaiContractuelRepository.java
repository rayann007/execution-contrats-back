package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.DelaiContractuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface DelaiContractuelRepository extends JpaRepository<DelaiContractuel, Long> {
    List<DelaiContractuel> findByContratId(Long contratId);

    @Query("SELECT d FROM DelaiContractuel d WHERE d.date = :targetDate")
    List<DelaiContractuel> findDelaisPourNotification(LocalDate targetDate);

        // Tous les délais non encore payés
        List<DelaiContractuel> findByPenalitePayeeFalse();
    }

