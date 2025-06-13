package com.leoni.execution.contrats.Services;


import com.leoni.execution.contrats.Models.CouleurIndicateur;
import com.leoni.execution.contrats.Models.Evaluation;
import com.leoni.execution.contrats.Models.EvaluationStatistiquesDTO;

import java.util.List;

public interface EvaluationService {
    EvaluationStatistiquesDTO genererStatistiquesContrat(Long contratId);
    Evaluation saveEvaluation(Evaluation evaluation);

    List<Evaluation> findByCouleur(CouleurIndicateur couleur);

}
