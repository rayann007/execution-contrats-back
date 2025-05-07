package com.leoni.execution.contrats.Models;

public class AuthRequest {
    private String email;
    private String motDePasse;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
