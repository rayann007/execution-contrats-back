package com.leoni.execution.contrats.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

// Cette annotation indique que cette classe est une entité JPA
@Entity
// On peut indiquer explicitement le nom de la table en base
@Table(name = "contrat")
public class Contrat {

    // Clé primaire avec auto-incrément
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom ou objet du contrat (peut être nul selon la base)
    @Column(name = "nomContrat", length = 255)
    private String nomContrat;

    // Enumération : Travaux, Prestation, Continu
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeContrat type;

    // Date de début du contrat
    @Column(name = "dateDebut")
    private java.time.LocalDate dateDebut;

    // Date de fin du contrat
    @Column(name = "dateFin")
    private java.time.LocalDate dateFin;

    // Enumération : en cours, terminé, résilié
    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private StatutContrat statut;

    // Service concerné par le contrat
    @Column(name = "serviceConcerne", length = 100)
    private String serviceConcerne;

    // Responsable côté LEONI
    @Column(name = "responsableLeoni", length = 100)
    private String responsableLeoni;

    // Email du responsable principal
    @Column(name = "emailResponsable", length = 100)
    private String emailResponsable;

    // Plusieurs emails dédiés séparés par virgules, ou autre format texte
    @Column(name = "emailsPersonnesDediees", columnDefinition = "TEXT")
    private String emailsPersonnesDediees;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
// === Getters et Setters générés automatiquement ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomContrat() { return nomContrat; }
    public void setNomContrat(String nomContrat) { this.nomContrat = nomContrat; }

    public TypeContrat getType() { return type; }
    public void setType(TypeContrat type) { this.type = type; }

    public java.time.LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(java.time.LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public java.time.LocalDate getDateFin() { return dateFin; }
    public void setDateFin(java.time.LocalDate dateFin) { this.dateFin = dateFin; }

    public StatutContrat getStatut() { return statut; }
    public void setStatut(StatutContrat statut) { this.statut = statut; }

    public String getServiceConcerne() { return serviceConcerne; }
    public void setServiceConcerne(String serviceConcerne) { this.serviceConcerne = serviceConcerne; }

    public String getResponsableLeoni() { return responsableLeoni; }
    public void setResponsableLeoni(String responsableLeoni) { this.responsableLeoni = responsableLeoni; }

    public String getEmailResponsable() { return emailResponsable; }
    public void setEmailResponsable(String emailResponsable) { this.emailResponsable = emailResponsable; }

    public String getEmailsPersonnesDediees() { return emailsPersonnesDediees; }
    public void setEmailsPersonnesDediees(String emailsPersonnesDediees) { this.emailsPersonnesDediees = emailsPersonnesDediees; }
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    private List<DelaiContractuel> delais;

    public List<DelaiContractuel> getDelais() {
        return delais;
    }

    public void setDelais(List<DelaiContractuel> delais) {
        this.delais = delais;
    }


}

