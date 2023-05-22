package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;

import com.core.mcprojetbibliotheque.Model.*;
import javafx.scene.control.Button;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public Utilisateur login(String eml, String pwd) throws SQLException, ClassNotFoundException {
            Utilisateur utilisateur=null;
        var cnx =dbConnexion.getConnection();

            System.out.println("***********************************");
            PreparedStatement statement = cnx.prepareStatement(TROUVE_UTILISATEUR);
            statement.setString(1, eml);
            statement.setString(2, pwd);
            // resultSet me renvoi le resultat de la requete select
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            String nom = null;
            String prenom = null;
            String username = null;
            String password = null;
            String email = null;
            String categorie = null;
            String certificat = null;
            while (resultSet.next()) {
                nom = resultSet.getString("nom");
                System.out.println("--------------->"+nom);
                prenom = resultSet.getString("prenom");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                categorie = resultSet.getString("categorie");
                certificat = resultSet.getString("certificat");
            }
            File fileCertificat=new File(certificat);
            System.out.println(categorie);
            switch (categorie){
                case "Enseignant":
                {
                    return utilisateur=new Etudiant(nom,prenom,username,password,email,categorie,new Date(System.currentTimeMillis()),fileCertificat);
                }
                case "Externe":
                {
                    return utilisateur=new Enseignant(nom,prenom,username,password,email,categorie,new Date(System.currentTimeMillis()),fileCertificat);
                }
                case "Gestionaire":
                {
                    return utilisateur=new Gestionnaire(nom,prenom,username,password,email,categorie);
                }
                case "Bibliothecaire":
                {
                    return utilisateur=new Bibliothecaire(nom,prenom,username,password,email,categorie);
                }


            }

        return utilisateur;
    }
    public void inscription(Abonne abonne) throws Exception {
        var nom= abonne.getNom();
        var prenom= abonne.getPrenom();
        var username= abonne.getUsername();
        var email= abonne.getEmail();
        var password= abonne.getPassword();
        var categorie= abonne.getCategorie();
        var certificatFile= abonne.getCertificat();
        System.out.println("chargement...");
        // le token : MTA5NjE3NzczMzkzNzEzNTc0Ng.Gl2vsZ.zh1DmUY9YNcTwiXlS8_N-aTGzM3787hvyOKCeA
        JDABuilder jdab= JDABuilder.createDefault("");
        jdab.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        jdab.addEventListeners(new ListenerAdapter() {
            @Override
            public void onReady(ReadyEvent event) {
            try {
                System.out.println(certificatFile.getPath());
                File file = new File(certificatFile.getPath());
                event.getJDA().getTextChannelById("1096178322393804871").sendFiles(FileUpload.fromData(file)).queue(message -> {
                    String pathOut=message.getAttachments().get(0).getUrl();
                    PreparedStatement statement= null;
                    try {
                        System.out.println("execution de la requete sql");
                        statement = dbConnexion.getConnection().prepareStatement(AJOUT_UTILISATEUR);
                        statement.setString(1,nom);
                        statement.setString(2,prenom);
                        statement.setString(3,username);
                        statement.setString(4,password);
                        statement.setString(5,email);
                        statement.setString(6,categorie);
                        statement.setString(7, pathOut);
                        statement.execute();
                        System.out.println("inscription finit");
                        System.out.println("apres btn");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
//                    sInscrire.setText("s'inscrire");
                System.out.println("file sent");
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        });
        jdab.setStatus(OnlineStatus.ONLINE);
        JDA jda=jdab.build();
        jda.updateCommands();
        System.out.println("*******************/////////////////");
    }
    public boolean checkPossiblity(String eml) throws SQLException, ClassNotFoundException {
    	 var cnx =dbConnexion.getConnection();
    	 
    	 PreparedStatement statement = cnx.prepareStatement(TROUVE_UTILISATEURWITHEMAIL);
    	 statement.setString(1, eml);
    	 ResultSet resultSet = statement.executeQuery();
    	  int nbrReservation=0;
    	  int nbrEmprunt=0;
    	  while (resultSet.next()) {
    	  nbrReservation = resultSet.getInt("nbrReservation");
    	  nbrEmprunt =resultSet.getInt("nbrEmprunt");
    	  }
    	  int total = nbrReservation+nbrEmprunt;
    	  if(total>=4) {
    		  return false;
    	  }
    	 
    	return true;
    }
    
    
    public ObservableList<reservation> getReservationList(String TypeReservation) throws SQLException{
    	LocalDate dateCurrent = LocalDate.now();
    	var cnx =dbConnexion.getConnection();
    	ObservableList<reservation> reservationList = FXCollections.observableArrayList();
    	 PreparedStatement statement = cnx.prepareStatement(TROUVE_ID_AND_DATE);
    	 statement.setString(1,TypeReservation);
    	 ResultSet resultSet = statement.executeQuery();
    	 int idUtilisateur=0;
   	  	 int idLivre=0;
   	  	 LocalDate dateReservation=null;
   	  	 LocalDate dateAcceptaion=null;
   	  	 int accepté=0;
   	  	 Boolean acceptéReservation =false;
   	  	 while (resultSet.next()) {
   		 idUtilisateur = resultSet.getInt("id_utilisateur");
   		 //pour trouver les information d utilisateur aprtir de id;
	   		 PreparedStatement statement2 = cnx.prepareStatement(TROUVE_INFORMATION_UTILISATEUR);
	   		 statement2.setString(1,String.valueOf(idUtilisateur));
	   	     ResultSet resultSet2 = statement2.executeQuery();
	   	     String email=null;
		   	 String nom=null;
		   	 String prenom=null;
		   	 while (resultSet2.next()) {
		   		 email=resultSet2.getString("email");
		   		 nom=resultSet2.getString("nom"); 
		   		 prenom=resultSet2.getString("prenom");
		   		
		   	 }
   		  //
   		  idLivre =resultSet.getInt("id_livre");
   		//pour trouver les information d livre aprtir de id;
	   		 PreparedStatement statement3 = cnx.prepareStatement(TROUVE_INFORMATION_LIVRE);
	   		 statement3.setString(1,String.valueOf(idLivre));
	   	     ResultSet resultSet3 = statement3.executeQuery();
	   	     String title = null;
		   	 int nbrExmplaire =0;
		   	 String auteur=null;
		   	 while (resultSet3.next()) {
		   		 title=resultSet3.getString("titre");
		   		 nbrExmplaire=resultSet3.getInt("nbExemplaires"); 
		   		 auteur=resultSet3.getString("auteur");
		   		 
		   	 }
		  //
   		  
   	      dateReservation=resultSet.getDate("date").toLocalDate();
   	      accepté=resultSet.getInt("accepté");
   	      if(resultSet.getDate("dateAcceptation") != null) {
   	    	  
   	    	  dateAcceptaion=resultSet.getDate("dateAcceptation").toLocalDate();
   	      }
   	      Boolean estEnRetard=false;
   	      if(accepté==0) {
   	    	acceptéReservation=false;
   	    	
   	      }else {
   	    	acceptéReservation=true;
   	    	
   	      }
   	      
   	      
   	      reservation reservation = new reservation(email,nom,prenom,title,auteur,Integer.valueOf(nbrExmplaire),dateReservation,acceptéReservation,dateAcceptaion,false);
   	      System.out.println(reservation.getDateAcceptaionOuRefusé());
   	      reservationList.add(reservation);
	   	   
   	      
    	  }
		return reservationList;
   	  	 
    	 
    	
    	
    	
    	
    }
  
    public int getIdLivre(String titre ,String auteur )  throws SQLException{
	   var cnx =dbConnexion.getConnection();
	   PreparedStatement statement = cnx.prepareStatement(TROUVE_ID_LIVRE);
  	   statement.setString(1,titre);
  	   statement.setString(2, auteur);
  	   ResultSet resultSet = statement.executeQuery();
  	   int idLivre = 0;
  	   while (resultSet.next()) {
  	   idLivre=resultSet.getInt("id");	
	  		 
	  		 
	  	 }
	  	System.out.println(titre+" "+auteur+" "+idLivre+"aaaaa");
    	return idLivre ;
		
    }
    public int getIdUtilisateur(String eml) throws SQLException{
 	   var cnx =dbConnexion.getConnection();
 	   PreparedStatement statement = cnx.prepareStatement(TROUVE_ID_UILISATEUR);
   	   statement.setString(1,eml);
   	   ResultSet resultSet = statement.executeQuery();
   	   int idutilisateur = 0;
   	   while (resultSet.next()) {
   	   idutilisateur=resultSet.getInt("id");	
 	  		 	 
 	  	 }
 	  	
     	return idutilisateur;
 		
     }
    public void addReservarion(int idLivre,int idUtilisateur)throws SQLException, ParseException{
    	var cnx =dbConnexion.getConnection();
    	

    	


    	PreparedStatement statement = cnx.prepareStatement(ADD_RESERVATION);
    	statement.setInt(1, idUtilisateur);
    	statement.setInt(2, idLivre);
    	statement.setDate(3,java.sql.Date.valueOf(LocalDate.now()));
    	//System.out.println("seccuss");
    	statement.execute();
    	
    }
    	
   
    
	
    
    
    public Boolean UpdateReservation(int idUtilisateur, int idLivre, LocalDate dateReservation ,String accepté)throws SQLException, ParseException {
    	
    		var cnx =dbConnexion.getConnection();
    		try {
   	       PreparedStatement statement = cnx.prepareStatement(UPDATE_RESERVATION);
   	       statement.setString(1,accepté);
   	       statement.setInt(2,idUtilisateur);
     	   statement.setInt(3,idLivre);
     	   statement.setDate(4,java.sql.Date.valueOf(dateReservation));
     	   
   	       int rowsUpdated = statement.executeUpdate();
     	   return true;
		} catch (Exception e) {
		   System.out.println(e.getMessage()+"false");
		   return false;
		}
    	   
    	  
		
	}
	public void supprimerReservation(int idUtilisateur, int idLivre, LocalDate dateReservation) throws SQLException {
		var cnx =dbConnexion.getConnection();
		PreparedStatement statement = cnx.prepareStatement(DELETE_RESERVATION);
		statement.setInt(1,idUtilisateur);
  	   	statement.setInt(2,idLivre);
  	   	statement.setDate(3, java.sql.Date.valueOf(dateReservation));
		statement.executeUpdate();
	}
	public void decrementerNombreExemplaire(int idLivre) throws SQLException {
		try {
			
			var cnx =dbConnexion.getConnection();
			PreparedStatement statement = cnx.prepareStatement(DECREMENTER_NOMBRE_EXEMPLAIRE);
			statement.setInt(1,idLivre);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void incrementerNombreExemplaire(int idLivre) throws SQLException {
		var cnx =dbConnexion.getConnection();
		PreparedStatement statement = cnx.prepareStatement(INCREMENTER_NOMBRE_EXEMPLAIRE);
		statement.setInt(1,idLivre);
		statement.executeUpdate();
		
	}
	public void AjouterEmprunt(int idUtilisateur, int idLivre) throws SQLException {
		try {
			var cnx =dbConnexion.getConnection();
			PreparedStatement statement = cnx.prepareStatement(AJOUTER_EMPRUNT);	
			statement.setInt(1,idUtilisateur);
			statement.setInt(2, idLivre);
			
			statement.executeUpdate();
			System.out.println("succes");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("hello");
		}
	}
	public void incrementerNbrReservationUtilisateur(int idUtilisateur) throws SQLException {
		var cnx =dbConnexion.getConnection();
		PreparedStatement statement = cnx.prepareStatement(INCREMENTER_NOMBRE_RESERVATION);
		statement.setInt(1, idUtilisateur);
		statement.executeUpdate();
	}
	public void decrementerNbrReservationUtilisateur(int idUtilisateur) throws SQLException {
		var cnx =dbConnexion.getConnection();
		PreparedStatement statement = cnx.prepareStatement(DECREMENTER_NOMBRE_RESERVATION);
		statement.setInt(1, idUtilisateur);
		statement.executeUpdate();
	}
	public void icrementerNombreEmprunt(int idUtilisateur)throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(INCREMENTER_NOMBRE_EMPRUNT);
        preparedStatement.setInt(1,idUtilisateur);
        preparedStatement.executeUpdate();
		
		
	}
	public boolean penaliserUtilisateur(String email) throws SQLException {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(PENALISER_UTILISATEUR);
	        LocalDate date = LocalDate.now().plusMonths(1);
	        preparedStatement.setDate(1, java.sql.Date.valueOf(date));
	        preparedStatement.setString(2,email);
	        preparedStatement.executeUpdate();
	        return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	public boolean checkPenalisation(String email) throws SQLException {
		
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(TROUVE_UTILISATEURWITHEMAIL);
	        preparedStatement.setString(1,email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        LocalDate date = null;
	        if(resultSet.next()) {
	        	if(resultSet.getDate("DateFinPenalisation")!=null) {
	        		
	        		date =resultSet.getDate("DateFinPenalisation").toLocalDate();
	        		
	        	}
	       
	        if(date == null || date.isBefore(LocalDate.now())) {
	        	return false;
	        }else {
	        	return true;
	        }
		
	        }
        
		return false;
	}
	public boolean checkPayement(String email) throws SQLException {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(TROUVE_UTILISATEURWITHEMAIL);
	        preparedStatement.setString(1,email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        LocalDate date = null;
	        String categorie = null;
	        if(resultSet.next()) {
	        	categorie = resultSet.getString("categorie");
	        	if(resultSet.getDate("dateFinPyement") != null) {
	        	
	        		date =resultSet.getDate("dateFinPyement").toLocalDate();
	        	}
	        }
	       


	        if(categorie.equals("Enseignant")) {
	        	System.out.println("success");
	        	return true;
	        }else {
	        	if(date == null  ) {
	        		return false;
	        	}else if( LocalDate.now().isAfter(date)){
	        		return false;
	        	}else {
	        		return true;
	        	}
	       	
	        }
	        
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
        
		
		
		
		
		
	}
	public boolean checkIfReservationExiste(int idUtilisateur, int idLivre) throws SQLException {
		var connection= dbConnexion.getConnection();
		 var preparedStatement=connection.prepareStatement(TROUVE_RESERVATION);
	        preparedStatement.setInt(1, idUtilisateur);
	        preparedStatement.setInt(2, idLivre);
	        preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            return true;
	        }
	        return false;
		
	}
	public ObservableList<Utilisateur> getAllUtilisateur() throws SQLException {
		 var connection= dbConnexion.getConnection();

	     var preparedStatement=connection.prepareStatement(ALL_UTILISATEUR);
	     ResultSet resultSet = preparedStatement.executeQuery();
	     ObservableList<Utilisateur> list = FXCollections.observableArrayList();
	     	
	        while (resultSet.next()){
	    
	        	 list.add(new Utilisateur(resultSet.getString("email"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("categorie"),false,false));
	        }
		return list;
	}
	public Boolean deleteUtilisateur(String email) throws SQLException {
		
		try {
			var connection= dbConnexion.getConnection();

		     var preparedStatement=connection.prepareStatement(DELETE_UTILISATEUR);
		     preparedStatement.setString(1, email);
		     preparedStatement.executeUpdate();
		     return true;
		} catch (Exception e) {
			return false;
		}
	     
	}
	public boolean Payement(String email, LocalDate date)throws Exception {
		try {
			var connection= dbConnexion.getConnection();
			if(date == null) {
				 var preparedStatement=connection.prepareStatement(UPDATE_PAYEMENT);
			     preparedStatement.setString(2, email);
			     
			     preparedStatement.executeUpdate();
				return true;
			}else {
				 var preparedStatement=connection.prepareStatement(UPDATE_PAYEMENT2);
				 preparedStatement.setDate(1, java.sql.Date.valueOf(date));
				 preparedStatement.setString(2, email);
			     
			     
			     preparedStatement.executeUpdate();
				return true;
			}
		     
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return false;
			
		}
		
	}
	
	
	public boolean AnnulerpenalisationUtilisateur(String email) throws SQLException {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(ANNULRE_PENALISER_UTILISATEUR);
	       
	        preparedStatement.setString(1,email);
	        preparedStatement.executeUpdate();
	        return true;
		} catch (Exception e) {
			return false;
		}
	
	
	}
	public boolean checkEmailIfExist(String email) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(CHECK_EMAIL_IF_EXIST);
        preparedStatement.setString(1,email);
        ResultSet resultSet =preparedStatement.executeQuery();
        
        while (resultSet.next()){
        	return true;
        }
		
		
		return false;
	}
	public boolean checkNombreExemplaire(int idLivre) throws SQLException {
		try {
			var Connection = dbConnexion.getConnection();
			var preparedStatement = Connection.prepareStatement(NOMBRE_EXEMPLAIRE);
			 preparedStatement.setInt(1,idLivre);
			 int nbrExemplaire=0;
		     ResultSet resultSet =preparedStatement.executeQuery();
		     while(resultSet.next()) {
		    	 nbrExemplaire = resultSet.getInt("nbExemplaires");
		     }
		     if(nbrExemplaire==0) {
		    	 return false;
		     }else {
		    	 return true;
		     }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	public int nombreUtilisateur(String categorie) throws SQLException {
		
		var Connection = dbConnexion.getConnection();
		var preparedStatement = Connection.prepareStatement(NOMBRE_ABONNONNES_SELON_TYPE);
		preparedStatement.setString(1,categorie);
		ResultSet resultSet =preparedStatement.executeQuery();
		int nombre = 0;
		while(resultSet.next()) {
			nombre = resultSet.getInt(1);
			
		}
		System.out.println(nombre);
		 return nombre;
		
		
	}
	
	
	}