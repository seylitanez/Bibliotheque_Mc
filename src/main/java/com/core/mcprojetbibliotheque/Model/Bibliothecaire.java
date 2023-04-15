package com.core.mcprojetbibliotheque.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bibliothecaire extends Utilisateur{
    List<Abonne> abonnes = new ArrayList<>();
    List<Livre> livres = new ArrayList<>();
    List<Emprunt> emprunts = new ArrayList<>();

    public Bibliothecaire(String nom, String prenom, String username, String password, String email, String categorie) {
        super(nom, prenom, username, password, email, categorie);
    }

    public void ajouterAbonne(Abonne abonne) {
        abonnes.add(abonne);
    }

    public void supprimerAbonne(Abonne abonne) {
        abonnes.remove(abonne);
    }

    public List<Abonne> getAbonnes() {
        return abonnes;
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public void supprimerLivre(Livre livre) {
        livres.remove(livre);
    }

    public List<Livre> getAllLivres() {
        return livres;
    }

    public void effectuerEmprunt(Livre livre, Abonne abonne) throws Exception {
        if (livre.estDisponible() && abonne.peutEmprunter()) {
            Emprunt emprunt = new Emprunt(livre, abonne, LocalDate.now());
            emprunts.add(emprunt);
            livre.emprunter();
//            abonne.ajouterEmprunt(emprunt);
        } else {
            throw new Exception("Impossible d'effectuer l'emprunt");
        }
    }
}
