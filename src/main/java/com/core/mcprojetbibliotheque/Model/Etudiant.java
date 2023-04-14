package com.core.mcprojetbibliotheque.Model;

import java.util.Date;

public class Etudiant extends Abonne {
    private boolean externe;
    private String password;

    public Etudiant(String nom, String prenom, String email, String password, String categorie, Date dateInscription, boolean externe, String password1) {
        super(nom, prenom, email, password, categorie, dateInscription);
        this.externe = externe;
        this.password = password1;
    }

    @Override
    public boolean peutEmprunter() {
        return false;
    }
}
