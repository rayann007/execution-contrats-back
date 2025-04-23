package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.EvaluationQualite;
import com.leoni.execution.contrats.Models.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationQualiteRepository extends JpaRepository<EvaluationQualite, Long> {
    List<EvaluationQualite> findByService(ServiceType service);
    List<EvaluationQualite> findByUtilisateurId(Long utilisateurId);
}
