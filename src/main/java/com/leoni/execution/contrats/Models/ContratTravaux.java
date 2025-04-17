package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;

import java.time.temporal.Temporal;
import java.util.List;

@Entity
@Table(name = "contrattravaux")
public class ContratTravaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // optionnel ici, mais ajouté pour cohérence
    private Long id;

    @Column(name = "montantAvance")
    private Float montantAvance;

    @Column(name = "montantFinal")
    private Float montantFinal;

    @Column(name = "retenueGarantie")
    private Float retenueGarantie;

    @OneToOne
    @JoinColumn(name = "contrat_id") // clé étrangère vers contrat(id)
    private Contrat contrat;

    // === Getters & Setters ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Float getMontantAvance() { return montantAvance; }
    public void setMontantAvance(Float montantAvance) { this.montantAvance = montantAvance; }

    public Float getMontantFinal() { return montantFinal; }
    public void setMontantFinal(Float montantFinal) { this.montantFinal = montantFinal; }

    public Float getRetenueGarantie() { return retenueGarantie; }
    public void setRetenueGarantie(Float retenueGarantie) { this.retenueGarantie = retenueGarantie; }

    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }

    public Temporal getDateFin() {
        return this.contrat.getDateFin();
    }

    public List<DelaiContractuel> getDelais() {
        return this.contrat.getDelais();
    }
}
