package com.leoni.execution.contrats.dto;

import java.util.List;

public class EvaluationDelaisDTO {
    private Long contratId;
    private List<Boolean> delaisRespectes;

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    public List<Boolean> getDelaisRespectes() {
        return delaisRespectes;
    }

    public void setDelaisRespectes(List<Boolean> delaisRespectes) {
        this.delaisRespectes = delaisRespectes;
    }
}
