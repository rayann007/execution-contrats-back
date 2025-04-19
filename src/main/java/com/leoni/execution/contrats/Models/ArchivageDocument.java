package com.leoni.execution.contrats.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "archive") // on mappe sur ta table existante
public class ArchivageDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateArchivage;

    @OneToOne
    @JoinColumn(name = "document_id") // correspond à ta colonne document_id
    private Document document;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateArchivage() {
        return dateArchivage;
    }

    public void setDateArchivage(LocalDate dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
