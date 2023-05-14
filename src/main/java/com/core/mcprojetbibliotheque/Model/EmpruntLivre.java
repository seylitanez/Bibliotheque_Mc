package com.core.mcprojetbibliotheque.Model;

import java.time.LocalDate;

public class EmpruntLivre {
		private int idEmprunt;
	    private int idUtilisateur;
	    private String email;
		private String nom;
	    private String prenom;
	    private int idLivre;
	    private String titre;
	    private String auteur;
	    private int nbrExemplaire;
	    private LocalDate dateEmprunt;
		private LocalDate dateRestitution; 
	    private boolean demandeProlonger;
	    private boolean prolonge;//
	    //private boolean prolongéRufusé;
	    private boolean EnRetard;
	    private LocalDate Delais;
	    

	    

		


		public EmpruntLivre(int idEmprunt,String email,String nom,String prenom,String titre,String auteur,int nbrExemplaire,LocalDate dateEmprunt,LocalDate dateRestitution,Boolean demandeProlonger , Boolean prolonger, Boolean enRetard ,LocalDate Delais) {
	    	this.idEmprunt=idEmprunt;
	        this.email = email;
	        this.nom=nom;
	        this.prenom=prenom;
	        this.titre=titre;
	        this.auteur=auteur;
	        this.nbrExemplaire=nbrExemplaire;
	        this.dateEmprunt=dateEmprunt;
	        this.dateRestitution=dateRestitution;
	        this.demandeProlonger =demandeProlonger;
	        this.prolonge = prolonger;
	        //this.prolongéAnnule=prolongéAnnule;
	        this.EnRetard = enRetard;
	        this.Delais=Delais;
	    
	    }

	 
	    

	    public LocalDate getDateEmprunt() {
	        return dateEmprunt;
	    }

	    public void setDateEmprunt(LocalDate dateEmprunt) {
	        this.dateEmprunt = dateEmprunt;
	    }

	    public LocalDate getDateRestitution() {
	        return dateRestitution;
	    }

	    public void setDateRestitution(LocalDate dateRestitution) {
	        this.dateRestitution = dateRestitution;
	    }

	    

	    public boolean isDemandeProlonger() {
	        return demandeProlonger;
	    }

	    public void setDemandeProlonger(boolean demandeProlonger) {
	        this.demandeProlonger = demandeProlonger;
	    }

	    public boolean isProlonge() {
	        return prolonge;
	    }

	    public void setProlonge(boolean prolonge) {
	        this.prolonge = prolonge;
	    }

	    public int getIdUtilisateur() {
			return idUtilisateur;
		}


		public void setIdUtilisateur(int idUtilisateur) {
			this.idUtilisateur = idUtilisateur;
		}


		public int getIdLivre() {
			return idLivre;
		}


		public void setIdLivre(int idLivre) {
			this.idLivre = idLivre;
		}
		
		 public int getIdEmprunt() {
				return idEmprunt;
			}

			public void setIdEmprunt(int idEmprunt) {
				this.idEmprunt = idEmprunt;
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
				auteur = auteur;
			}




			public boolean isEnRetard() {
				return EnRetard;
			}




			public void setEnRetard(boolean enRetard) {
				EnRetard = enRetard;
			}

			
			
			
			public void EstEnRetard() {
				if ( LocalDate.now().isAfter( (this.dateEmprunt).plusMonths(1)  )) {
					this.setEnRetard(true);
				}
				}
			
			public void dernierDelais() {
				this.setDelais(this.dateEmprunt.plusMonths(1));
				
			}
			
			public LocalDate getDelais() {
				return Delais;
			}




			public void setDelais(LocalDate delais) {
				Delais = delais;
			}




			public int getNbrExemplaire() {
				return nbrExemplaire;
			}




			public void setNbrExemplaire(int nbrExemplaire) {
				this.nbrExemplaire = nbrExemplaire;
			}
			
			
			
			
	}


