package com.core.mcprojetbibliotheque.Model;

import java.util.Date;

public class Enseignant extends Abonne {
    private boolean enseignant ;
    public Enseignant(String nom, String prenom, String password, String categorie, Date dateInscription) {
        super(nom, prenom,password,categorie,dateInscription);
        this.enseignant= true;
    }
    @Override
    public boolean peutEmprunter() {
        return true;
    }
}
