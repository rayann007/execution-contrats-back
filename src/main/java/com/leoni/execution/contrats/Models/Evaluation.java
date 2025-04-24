// 1. Entité Evaluation
package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    private LocalDate dateEvaluation;

    @Enumerated(EnumType.STRING)
    @Column(name = "couleurIndicateur")
    private CouleurIndicateur couleurIndicateur;
    // 'Vert', 'Jaune', 'Orange', 'Rouge'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestataire_id")
    private Prestataire prestataire;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }

    public CouleurIndicateur getCouleurIndicateur() {
        return couleurIndicateur;
    }

    public void setCouleurIndicateur(CouleurIndicateur couleurIndicateur) {
        this.couleurIndicateur = couleurIndicateur;
    }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }

    public Prestataire getPrestataire() { return prestataire; }
    public void setPrestataire(Prestataire prestataire) { this.prestataire = prestataire; }
}