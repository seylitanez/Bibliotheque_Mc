package com.core.mcprojetbibliotheque.Model;

import java.time.LocalDate;
import java.util.Date;

public class reservation {
	String email,nom,prenom,userName,title ;
	Integer nbrExemplaire;
	LocalDate dateReservation;
	Boolean accepté;
	LocalDate dateAcceptaion;
	String auteur;
	Boolean estEnRetard ;
	

	public Boolean getEstEnRetard() {
		return estEnRetard;
	}

	public void setEstEnRetard(Boolean estEnRetard) {
		this.estEnRetard = estEnRetard;
	}

	public reservation(String email, String nom, String prenom,  String title,String auteur, Integer nbrExemplaire,
			LocalDate dateReservation,Boolean accepté,LocalDate dateAcceptaion ,Boolean estEnRetard ) {
		super();
		this.estEnRetard = estEnRetard;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.auteur=auteur;
		this.title = title;
		this.nbrExemplaire = nbrExemplaire;
		this.dateReservation = dateReservation;
		this.accepté=accepté;
		this.dateAcceptaion =dateAcceptaion ;
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
	public LocalDate getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}

	public LocalDate getDateAcceptaionOuRefusé() {
		return dateAcceptaion;
	}

	public void setDateAcceptaionOuRefusé(LocalDate dateAcceptaionOuRefusé) {
		this.dateAcceptaion = dateAcceptaionOuRefusé;
	}
	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public void EstEnRetard() {
		if (dateAcceptaion != null && LocalDate.now().isAfter( (dateAcceptaion).plusWeeks(1)  )) {
			this.setEstEnRetard(true);
		}
	}
	
	
	
	
	
	

	
	
	
	
}
