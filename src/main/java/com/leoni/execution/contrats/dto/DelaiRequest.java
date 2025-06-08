package com.leoni.execution.contrats.dto;
public class DelaiRequest {
    public String date;
    public Integer joursRetard;
    public Float montantPenaliteJournalier;
    public Boolean penalitePayee;
    public String commentaire;
    public Boolean respecteDelai;
    public Long contrat_id;
}
