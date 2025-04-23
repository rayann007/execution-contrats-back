package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.Personnel;
import com.leoni.execution.contrats.Models.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    List<Personnel> findByService(ServiceType service);

    @Query("SELECT p FROM Personnel p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.fonction) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Personnel> searchByNomOrFonction(@Param("keyword") String keyword);

    List<Personnel> findByServiceOrderByNomAsc(ServiceType service);


}
