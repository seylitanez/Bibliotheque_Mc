package com.core.mcprojetbibliotheque.Model;

import java.util.Date;

public class Etudiant extends Abonne {
    private boolean externe;
    private String password;

    public Etudiant(String nom, String prenom, String password, String categorie, Date dateInscription,boolean externe) {
        super(nom,prenom,password,categorie,dateInscription);
        this.externe = externe;
    }
    @Override
    public boolean peutEmprunter() {
        return false;
    }
}
