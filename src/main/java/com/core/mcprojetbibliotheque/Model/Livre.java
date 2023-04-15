package com.core.mcprojetbibliotheque.Model;

import java.util.ArrayList;
import java.util.List;

public class Livre {
    private String titre;
    private String auteur;
    private int nbExemplaires;
    private String codeRayon;
    private String photo;
    private ArrayList<String> references;
    private boolean disponibilite;
    public Livre(String titre, String auteur, int nbExemplaires, String codeRayon, String photo, ArrayList<String> references) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaires = nbExemplaires;
        this.codeRayon = codeRayon;
        this.references = references;
        this.photo=photo;
        this.disponibilite = true;
    }
    public void changeDisponibilite(){
        if (getNbExemplaires()== 0){
            setDisponibilite(false);
        }
        else {
            setDisponibilite(true);
        }
    }
    public void suppNbExemplaires(){
        setNbExemplaires( getNbExemplaires() -1);
        changeDisponibilite();
    }
    public void AjoutNbExemplaires(){
        setNbExemplaires( getNbExemplaires() +1);
        changeDisponibilite();
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public int getNbExemplaires() {
        return nbExemplaires;
    }
    public void setNbExemplaires(int nbExemplaires) {
        if (nbExemplaires>0) this.nbExemplaires = nbExemplaires;
    }
    public String getCodeRayon() {
        return codeRayon;
    }
    public void setCodeRayon(String codeRayon) {
        this.codeRayon = codeRayon;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public ArrayList<String> getReferences() {
        return references;
    }
    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
    public boolean isDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
}