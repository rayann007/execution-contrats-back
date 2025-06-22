package com.leoni.execution.contrats.Repositories;

import com.leoni.execution.contrats.Models.CouleurIndicateur;
import com.leoni.execution.contrats.Models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findByContratId(Long contratId);

    List<Evaluation> findByPrestataireId(Long prestataireId);

    // ✅ utiliser l'enum directement
    List<Evaluation> findByCouleurIndicateur(CouleurIndicateur couleur);


}
