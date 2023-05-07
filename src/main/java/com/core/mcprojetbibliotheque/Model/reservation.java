package com.core.mcprojetbibliotheque.Model;

import java.util.Date;

public class reservation {
	String email,nom,prenom,userName,title ;
	Integer nbrExemplaire;
	Date dateReservation;
	Boolean accepté;
	Date dateAcceptaionOuRefusé;
	public reservation(String email, String nom, String prenom, String userName, String title, Integer nbrExemplaire,
			Date dateReservation,Boolean accepté,Date dateAcceptaionOuRefusé ) {
		super();
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.userName = userName;
		this.title = title;
		this.nbrExemplaire = nbrExemplaire;
		this.dateReservation = dateReservation;
		this.accepté=accepté;
		this.dateAcceptaionOuRefusé =dateAcceptaionOuRefusé ;
	}

	public Boolean getAccepté() {
		return accepté;
	}
	public void setAccepté(Boolean accepté) {
		this.accepté = accepté;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNbrExemplaire() {
		return nbrExemplaire;
	}
	public void setNbrExemplaire(Integer nbrExemplaire) {
		this.nbrExemplaire = nbrExemplaire;
	}
	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateAcceptaionOuRefusé() {
		return dateAcceptaionOuRefusé;
	}

	public void setDateAcceptaionOuRefusé(Date dateAcceptaionOuRefusé) {
		this.dateAcceptaionOuRefusé = dateAcceptaionOuRefusé;
	}
	
	
	
	
	
	
	
	

	
	
	
	
}
