package com.core.mcprojetbibliotheque.Model;

public class Gestionnaire extends Utilisateur{

    private String nom;
    private String prenom;
    private String email;

    public Gestionnaire(String nom, String prenom, String username, String password, String email, String categorie, String nom1, String prenom1, String email1) {
        super(nom, prenom, username, password, email, categorie);
        this.nom = nom1;
        this.prenom = prenom1;
        this.email = email1;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
