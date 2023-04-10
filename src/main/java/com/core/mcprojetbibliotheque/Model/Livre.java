package com.core.mcprojetbibliotheque.Model;

import java.util.List;

public class Livre {
    private String titre;
    private String auteur;
    private int nbExemplaires;
    private String codeRayon;
    private List<String> references;
    public Livre(String titre, String auteur, int nbExemplaires, String codeRayon, List<String> references) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaires = nbExemplaires;
        this.codeRayon = codeRayon;
        this.references = references;
    }
    public String getTitre() {
        return this.titre;
    }
    public String getAuteur() {
        return this.auteur;
    }
    public int getNbExemplaires() {
        return this.nbExemplaires;
    }
    public String getCodeRayon() {
        return this.codeRayon;
    }
    public List<String> getReferences() {
        return this.references;
    }
    public void setNombreExemplaires(int nombreExemplaires) {
        this.nbExemplaires = nombreExemplaires;
    }
    public void ajouterExemplaire(String references) {
        this.references.add(references);
        this.nbExemplaires++;
    }
    public void retirerExemplaire(String references) {
        this.references.remove(references);
        this.nbExemplaires--;
    }

    public boolean estDisponible() {
        return false;
    }

    public void emprunter() {
    }
}


