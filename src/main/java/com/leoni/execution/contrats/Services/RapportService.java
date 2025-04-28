package com.leoni.execution.contrats.Services;

import com.leoni.execution.contrats.Models.Rapport;

public interface RapportService {
    Rapport genererRapportPourContrat(Long contratId);

    Rapport getRapportByContrat(Long contratId);
}
