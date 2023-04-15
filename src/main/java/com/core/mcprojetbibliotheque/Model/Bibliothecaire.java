package com.core.mcprojetbibliotheque.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bibliothecaire extends Utilisateur{
    List<Abonne> abonnes = new ArrayList<>();
    List<Livre> livres = new ArrayList<>();


    public Bibliothecaire(String nom, String prenom, String username, String password, String email, String categorie, List<Abonne> abonnes, List<Livre> livres) {
        super(nom, prenom, username, password, email, categorie);
        this.abonnes = abonnes;
        this.livres = livres;
    }

    public Bibliothecaire(String nom, String prenom, String username, String password, String email, String categorie) {
        super(nom, prenom, username, password, email, categorie);
        this.abonnes = abonnes;
        this.livres = livres;
    }





}
