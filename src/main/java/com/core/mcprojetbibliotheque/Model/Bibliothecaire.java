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
            abonne.ajouterLivre(livre);
        } else {
            throw new Exception("Impossible d'effectuer l'emprunt");
        }
    }
}
