package com.leoni.execution.contrats.Models;

import java.time.LocalDate;

public class DocumentDTO {
    private Long id;
    private String nomFichier;
    private LocalDate dateCreation;

    public DocumentDTO(Long id, String nomFichier, LocalDate dateCreation) {
        this.id = id;
        this.nomFichier = nomFichier;
        this.dateCreation = dateCreation;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }
}