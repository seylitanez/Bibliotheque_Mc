package com.core.mcprojetbibliotheque.Model;

import java.util.ArrayList;
public class Gestionnaire extends Utilisateur{
    private ArrayList<Abonne> abonnes=new ArrayList<>();

    public Gestionnaire(String nom, String prenom, String username, String password, String email, String categorie, ArrayList<Abonne> abonnes) {
        super(nom, prenom, username, password, email, categorie);
        this.abonnes = abonnes;
    }
    public Gestionnaire(String nom, String prenom, String username, String password, String email, String categorie) {
        super(nom, prenom, username, password, email, categorie);
        this.abonnes = abonnes;
    }

    public ArrayList<Abonne> getAbonnes() {
        return abonnes;
    }
    public void removeAbonne(Abonne abonne){
        abonnes.remove(abonne);
    }

    public void setAbonnes(ArrayList<Abonne> abonnes) {
        this.abonnes = abonnes;
    }
}