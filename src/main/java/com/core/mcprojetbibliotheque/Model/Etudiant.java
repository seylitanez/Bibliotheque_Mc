package com.core.mcprojetbibliotheque.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Etudiant extends Abonne {
    public Etudiant(String nom, String prenom, String username, String password, String email, String categorie, Date dateInscription, Date dateEmpunt, File certificat, ArrayList<Livre> empreunts, boolean penalite, boolean compteValide) {
        super(nom, prenom, username, password, email, categorie, dateInscription, dateEmpunt, certificat, empreunts, penalite, compteValide);
    }

    public Etudiant(String nom, String prenom, String username, String password, String email, String categorie, Date dateInscription, File certificat) {
        super(nom, prenom, username, password, email, categorie, dateInscription, certificat);
    }

    @Override
    public boolean peutEmprunter() {
        return false;
    }
}
