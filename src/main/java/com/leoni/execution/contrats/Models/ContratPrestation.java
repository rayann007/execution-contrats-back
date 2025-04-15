package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "contratprestationservice")
public class ContratPrestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mission")
    private String mission;

    @OneToOne
    @JoinColumn(name = "contrat_id") // clé étrangère vers table contrat
    private Contrat contrat;

    // === Getters & Setters ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMission() { return mission; }
    public void setMission(String mission) { this.mission = mission; }

    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }
}
