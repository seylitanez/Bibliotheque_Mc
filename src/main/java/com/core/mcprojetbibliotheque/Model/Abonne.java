package com.core.mcprojetbibliotheque.Model;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import java.io.File;
import java.util.*;
public class Abonne extends Utilisateur{
    private Date dateInscription,dateEmpunt;
    private File certificat;
    private ArrayList<Livre> empreunts =new ArrayList<>();
    private boolean penalite,compteValide;
    public Abonne(String nom, String prenom, String username, String password, String email, String categorie, Date dateInscription, Date dateEmpunt, File certificat, ArrayList<Livre> empreunts, boolean penalite, boolean compteValide) {
        super(nom, prenom, username, password, email, categorie);
        this.dateInscription = dateInscription;
        this.dateEmpunt = dateEmpunt;
        this.certificat = certificat;
        this.empreunts = empreunts;
        this.penalite = penalite;
        this.compteValide = compteValide;
    }
    public Abonne(String nom, String prenom, String username, String password, String email, String categorie, Date dateInscription, File certificat) {
        super(nom, prenom, username, password, email, categorie);
        this.dateInscription = dateInscription;
        this.certificat = certificat;
    }
    public File getCertificat() {
        return certificat;
    }
    public void setCertificat(File certificat) {
        this.certificat = certificat;
    }
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
    public void sInscrire() throws Exception {
        ConnectionService connectionService=new ConnectionService();
        connectionService.inscription(this);
    }
}