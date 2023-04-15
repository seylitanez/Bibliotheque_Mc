package com.core.mcprojetbibliotheque.Model;
import java.util.ArrayList;
import java.util.Date;
public class Abonne extends Utilisateur{
    private Date dateInscription,dateEmpunt;

    private ArrayList<Livre> empreunts =new ArrayList<>();
    private boolean penalite,compteValide;



    public Date getDateEmpunt() {
        return dateEmpunt;
    }

    public boolean isCompteValide() {
        return compteValide;
    }

    public void setCompteValide(boolean compteValide) {
        this.compteValide = compteValide;
    }

    public void setDateEmpunt(Date dateEmpunt) {
        this.dateEmpunt = dateEmpunt;
    }

    public Abonne(String nom, String prenom, String username, String password, String email, String categorie, Date dateInscription, Date dateEmpunt, ArrayList<Livre> empreunts, boolean penalite, boolean compteValide) {
        super(nom, prenom, username, password, email, categorie);
        this.dateInscription = dateInscription;
        this.dateEmpunt = dateEmpunt;
        this.empreunts = empreunts;
        this.penalite = penalite;
        this.compteValide = compteValide;
    }

    public ArrayList<Livre> getEmpreunts() {
        return empreunts;
    }

    public void setEmpreunts(ArrayList<Livre> empreunts) {
        this.empreunts = empreunts;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean isPenalite() {
        return penalite;
    }



    public Date getDateInscription() {
        return dateInscription;
    }
    public boolean hasPenalite() {
        return penalite;
    }
    public void setPenalite(boolean penalite) {
        this.penalite = penalite;
    }
    public boolean peutEmprunter() {
        return !penalite && isCompteValide();
    }
    public void ajouterLivre(Livre livre) {
        if (empreunts.size()<3)empreunts.add(livre);
    }

}
