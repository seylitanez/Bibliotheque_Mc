package com.core.mcprojetbibliotheque.Model;
import java.time.LocalDate;
public class Emprunt {
    private Livre livre;
    private Abonne abonne;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    public Emprunt(Livre livre, Abonne abonne, LocalDate dateEmprunt) {
        this.livre = livre;
        this.abonne = abonne;
        this.dateEmprunt = dateEmprunt;
    }
    public Livre getLivre() {
        return livre;
    }
    public Abonne getAbonne() {
        return abonne;
    }
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    public LocalDate getDateRetour() {
        return dateRetour;
    }
    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }
    public boolean estEnRetard() {
        if (dateRetour == null) {
            LocalDate dateActuelle = LocalDate.now();
            return dateActuelle.isAfter(dateEmprunt.plusWeeks(3));
        } else {
            return dateRetour.isAfter(dateEmprunt.plusWeeks(3));
        }
    }
    public boolean peutEtreProlonge() {
        if (dateRetour == null) {
            LocalDate dateActuelle = LocalDate.now();
            return dateActuelle.isBefore(dateEmprunt.plusWeeks(5));
        } else {
            return false;
        }
    }
    public void prolonger() {
        if (peutEtreProlonge()) {
            dateEmprunt = dateEmprunt.plusWeeks(2);
        }
    }
}
