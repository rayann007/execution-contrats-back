package com.leoni.execution.contrats.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "delaicontractuel")
public class DelaiContractuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "joursRetard")
    private Integer joursRetard;

    @Column(name = "montantPenaliteJournalier")
    private Float montantPenaliteJournalier;

    @Column(name = "penalitePayee")
    private Boolean penalitePayee = false;

    @Column(name = "montantTotal")
    private Float montantTotal;

    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getJoursRetard() {
        return joursRetard;
    }

    public void setJoursRetard(Integer joursRetard) {
        this.joursRetard = joursRetard;
    }

    public Float getMontantPenaliteJournalier() {
        return montantPenaliteJournalier;
    }

    public void setMontantPenaliteJournalier(Float montantPenaliteJournalier) {
        this.montantPenaliteJournalier = montantPenaliteJournalier;
    }

    public Boolean getPenalitePayee() {
        return penalitePayee;
    }

    public void setPenalitePayee(Boolean penalitePayee) {
        this.penalitePayee = penalitePayee;
    }

    public Float getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Float montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
