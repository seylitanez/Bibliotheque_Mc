package com.core.mcprojetbibliotheque.Model;

import java.util.Date;

public class Enseignant extends Abonne {
    private boolean enseignant ;

    public Enseignant(String nom, String prenom, String email, String password, String categorie, Date dateInscription, boolean enseignant) {
        super(nom, prenom, email, password, categorie, dateInscription);
        this.enseignant = enseignant;
    }

    @Override
    public boolean peutEmprunter() {
        return true;
    }
}
