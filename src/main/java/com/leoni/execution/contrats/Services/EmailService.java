package com.leoni.execution.contrats.Services;

public interface EmailService {
    void envoyerMailPersonnesDediees(String emails, String sujet, String contenu);
}
