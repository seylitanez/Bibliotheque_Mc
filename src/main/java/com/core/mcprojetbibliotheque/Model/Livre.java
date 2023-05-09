package com.core.mcprojetbibliotheque.Model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Livre {
	private int idLivre;
    private String titre;
    private String auteur;
    private int nbExemplaires;
    private int codeRayon;
    private String photo;
    private ArrayList<String> references;
    private boolean disponibilite;
    private String filiere;
    private Date dateAjouter;
    public Livre(String titre, String auteur, int nbExemplaires, int codeRayon, String photo, ArrayList<String> references) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaires = nbExemplaires;
        this.codeRayon = codeRayon;
        this.references = references;
        this.photo=photo;
        this.disponibilite = true;
    }
    public Livre(int idLivre,String titre, String auteur, int nbExemplaires, int codeRayon, String photo, String filiere,Date dateAjouter) {
        this.idLivre=idLivre;
    	this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaires = nbExemplaires;
        this.codeRayon = codeRayon;
        this.photo=photo;
        this.disponibilite = true;
        this.setFiliere(filiere);
        this.setDateAjouter(dateAjouter);
    }
    public void changeDisponibilite(){
        if (getNbExemplaires()== 0){
            setDisponibilite(false);
        }
        else {
            setDisponibilite(true);
        }
    }
    public void suppNbExemplaires(){
        setNbExemplaires( getNbExemplaires() -1);
        changeDisponibilite();
    }
    public void AjoutNbExemplaires(){
        setNbExemplaires( getNbExemplaires() +1);
        changeDisponibilite();
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public int getNbExemplaires() {
        return nbExemplaires;
    }
    public void setNbExemplaires(int nbExemplaires) {
        if (nbExemplaires>0) this.nbExemplaires = nbExemplaires;
    }
    public int getCodeRayon() {
        return codeRayon;
    }
    public void setCodeRayon(int codeRayon) {
        this.codeRayon = codeRayon;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public ArrayList<String> getReferences() {
        return references;
    }
    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
    public boolean isDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
	public int getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public Date getDateAjouter() {
		return dateAjouter;
	}
	public void setDateAjouter(Date dateAjouter) {
		this.dateAjouter = dateAjouter;
	}
}