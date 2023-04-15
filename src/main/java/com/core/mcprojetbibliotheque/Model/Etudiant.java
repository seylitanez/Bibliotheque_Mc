package com.core.mcprojetbibliotheque.Model;

import java.util.ArrayList;
import java.util.Date;

public class Etudiant extends Abonne {
    public Etudiant(String nom, String prenom, String username, String password, String email, String categorie, Date dateInscription, Date dateEmpunt, ArrayList<Livre> empreunts, boolean penalite, boolean compteValide) {
        super(nom, prenom, username, password, email, categorie, dateInscription, dateEmpunt, empreunts, penalite, compteValide);
    }
    @Override
    public boolean peutEmprunter() {
        return false;
    }
}
