package com.leoni.execution.contrats.Models;

public class EvaluationQualiteDTO {

    private String service;
    private String remarque;
    private String nomFichier;
    private String nomChef;

    public EvaluationQualiteDTO(String service, String remarque, String nomFichier, String nomChef) {
        this.service = service;
        this.remarque = remarque;
        this.nomFichier = nomFichier;
        this.nomChef = nomChef;
    }

    // Getters (ou @Getter si tu utilises Lombok)
    public String getService() { return service; }
    public String getRemarque() { return remarque; }
    public String getNomFichier() { return nomFichier; }
    public String getNomChef() { return nomChef; }
}
