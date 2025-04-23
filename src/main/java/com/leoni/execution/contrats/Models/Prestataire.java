package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "prestataire")
public class Prestataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(name = "Num_Tel")
    private String numTel;

    @Column(name = "E-mail")
    private String email;

    private String typePrestataire;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypePrestataire() {
        return typePrestataire;
    }

    public void setTypePrestataire(String typePrestataire) {
        this.typePrestataire = typePrestataire;
    }
}
