package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFichier;

    private LocalDate dateCreation;

    @Lob
    private byte[] fichier;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomFichier() { return nomFichier; }
    public void setNomFichier(String nomFichier) { this.nomFichier = nomFichier; }

    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }

    public byte[] getFichier() { return fichier; }
    public void setFichier(byte[] fichier) { this.fichier = fichier; }

    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }
}
