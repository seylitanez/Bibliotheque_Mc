package com.core.mcprojetbibliotheque.Model;

import java.util.Date;

public class Utilisateur {
    private String nom,prenom,username,password,email,categorie;
    private int id;
    private Boolean penalisé;
    private Boolean payement;
    

    public Utilisateur(String nom, String prenom, String username, String password, String email, String categorie) {
        this.nom = nom;
        this.prenom = prenom;
        this.username=username;
        this.password = password;
        this.email = email;
        this.categorie = categorie;
        
    }
    public Utilisateur(String email, String nom ,String prenom, String categorie,boolean penalisé,boolean payement ) {
    	this.email=email;
    	this.nom=nom;
    	this.prenom=prenom;
    	this.categorie=categorie;
    	this.penalisé=penalisé;
        this.payement=payement;
    	
    }
     
    
    
    
    public void login(String username, String password){

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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	public Boolean getPenalisé() {
		return penalisé;
	}
	public void setPenalisé(Boolean penalisé) {
		this.penalisé = penalisé;
	}
	public Boolean getPayement() {
		return payement;
	}
	public void setPayement(Boolean payement) {
		this.payement = payement;
	}
}