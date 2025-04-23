package com.leoni.execution.contrats.DTO;

public class EvaluationStatistiquesDTO {

    private long totalDelais;
    private long delaisRespectes;
    private long delaisNonRespectes;

    private long delaisVerts;
    private long delaisJaunes;
    private long delaisOranges;
    private long delaisRouges;

    private String tauxRespect;

    // Getters & Setters
    public long getTotalDelais() {
        return totalDelais;
    }

    public void setTotalDelais(long totalDelais) {
        this.totalDelais = totalDelais;
    }

    public long getDelaisRespectes() {
        return delaisRespectes;
    }

    public void setDelaisRespectes(long delaisRespectes) {
        this.delaisRespectes = delaisRespectes;
    }

    public long getDelaisNonRespectes() {
        return delaisNonRespectes;
    }

    public void setDelaisNonRespectes(long delaisNonRespectes) {
        this.delaisNonRespectes = delaisNonRespectes;
    }

    public long getDelaisVerts() {
        return delaisVerts;
    }

    public void setDelaisVerts(long delaisVerts) {
        this.delaisVerts = delaisVerts;
    }

    public long getDelaisJaunes() {
        return delaisJaunes;
    }

    public void setDelaisJaunes(long delaisJaunes) {
        this.delaisJaunes = delaisJaunes;
    }

    public long getDelaisOranges() {
        return delaisOranges;
    }

    public void setDelaisOranges(long delaisOranges) {
        this.delaisOranges = delaisOranges;
    }

    public long getDelaisRouges() {
        return delaisRouges;
    }

    public void setDelaisRouges(long delaisRouges) {
        this.delaisRouges = delaisRouges;
    }

    public String getTauxRespect() {
        return tauxRespect;
    }

    public void setTauxRespect(String tauxRespect) {
        this.tauxRespect = tauxRespect;
    }
}
