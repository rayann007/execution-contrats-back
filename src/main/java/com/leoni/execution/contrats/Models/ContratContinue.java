package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "contratcontinue")
public class ContratContinue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeService")
    private typeService typeService;

    @OneToOne
    @JoinColumn(name = "contrat_id") // clé étrangère
    private Contrat contrat;

    // === Getters & Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public typeService getTypeService() {
        return typeService;
    }

    public void setTypeService(typeService typeService) {
        this.typeService = typeService;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
