package com.core.mcprojetbibliotheque.Model;
import java.util.Date;
public class Abonne {
    private String nom,prenom,password,categorie;
    private Date dateInscription;
    private int nombreLivresEmpruntes;
    private boolean penalite;
    public Abonne(String nom, String prenom,String password, String categorie, Date dateInscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.password=password;
        this.categorie = categorie;
        this.dateInscription = dateInscription;
        this.nombreLivresEmpruntes = 0;
        this.penalite = false;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getCategorie() {
        return categorie;
    }
    public Date getDateInscription() {
        return dateInscription;
    }
    public int getNombreLivresEmpruntes() {
        return nombreLivresEmpruntes;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNombreLivresEmpruntes(int nombreLivresEmpruntes) {
        this.nombreLivresEmpruntes = nombreLivresEmpruntes;
    }
    public boolean hasPenalite() {
        return penalite;
    }
    public void setPenalite(boolean penalite) {
        this.penalite = penalite;
    }
    public boolean peutEmprunter() {
        return false;
    }
    public void ajouterEmprunt(Emprunt emprunt) {
    }
}