package com.core.mcprojetbibliotheque.Model;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {

      public static List<Abonne> abonnes = new ArrayList<>();
      public static List<Livre> livres = new ArrayList<>();
    private Bibliotheque() {

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
    public void effectuerEmprunt(Livre livre, Abonne abonne) {
        if (livre.isDisponibilite() && abonne.peutEmprunter()) {
            abonne.ajouterLivre(livre);
            livre.suppNbExemplaires();
        } else {
            System.out.println("livre indisponible ou abonne ne peut plus empreinter");
        }
    }
}
