package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class EvaluationQualite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String remarque;

    private String nomFichier;

    private LocalDate date;

    @Lob
    private byte[] fichier;

    @Enumerated(EnumType.STRING)
    private ServiceType service;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Utilisateur utilisateur; // lien vers chef de service

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRemarque() { return remarque; }
    public void setRemarque(String remarque) { this.remarque = remarque; }

    public String getNomFichier() { return nomFichier; }
    public void setNomFichier(String nomFichier) { this.nomFichier = nomFichier; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public byte[] getFichier() { return fichier; }
    public void setFichier(byte[] fichier) { this.fichier = fichier; }

    public ServiceType getService() { return service; }
    public void setService(ServiceType service) { this.service = service; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}
