package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.DTO.EvaluationStatistiquesDTO;

public interface EvaluationService {
    EvaluationStatistiquesDTO genererStatistiquesContrat(Long contratId);
}
