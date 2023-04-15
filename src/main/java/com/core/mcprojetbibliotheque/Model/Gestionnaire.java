package com.core.mcprojetbibliotheque.Model;

import java.util.ArrayList;

public class Gestionnaire extends Utilisateur{
    private String nom;
    private String prenom;
    private String email;

    private ArrayList<Abonne> abonnes=new ArrayList<>();

    public Gestionnaire(String nom, String prenom, String username, String password, String email, String categorie, String nom1, String prenom1, String email1, ArrayList<Abonne> abonnes) {
        super(nom, prenom, username, password, email, categorie);
        this.nom = nom1;
        this.prenom = prenom1;
        this.email = email1;
        this.abonnes = abonnes;
    }

    public ArrayList<Abonne> getAbonnes() {
        return abonnes;
    }


    public void removeAbonne(Abonne abonne){
        abonnes.remove(abonne);
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
