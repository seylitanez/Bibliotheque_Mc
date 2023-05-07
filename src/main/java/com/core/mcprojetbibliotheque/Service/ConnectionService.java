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
        JDABuilder jdab= JDABuilder.createDefault("MTA5NjE3NzczMzkzNzEzNTc0Ng.Gzj8qY.YIiHfiv0Tta1E5zoMlEtakrTt1gkF2BMAEDiek");
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
    	  System.out.println(nbrReservation+" "+nbrEmprunt);
    	 
    	return true;
    }
    
    
    public ObservableList<reservation> getReservationList(String TypeReservation) throws SQLException{
    	 var cnx =dbConnexion.getConnection();
    	ObservableList<reservation> reservationList = FXCollections.observableArrayList();
    	 PreparedStatement statement = cnx.prepareStatement(TROUVE_ID_AND_DATE);
    	 statement.setString(1,TypeReservation);
    	 ResultSet resultSet = statement.executeQuery();
    	 int idUtilisateur=0;
   	  	 int idLivre=0;
   	  	 Date dateReservation=null;
   	  	 Date dateAcceptaionOuRefusé=null;
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
		   	 String username=null;
		   	 while (resultSet2.next()) {
		   		 email=resultSet2.getString("email");
		   		 nom=resultSet2.getString("nom"); 
		   		 prenom=resultSet2.getString("prenom");
		   		 username=resultSet2.getString("username");
		   	 }
   		  //
   		  idLivre =resultSet.getInt("id_livre");
   		//pour trouver les information d livre aprtir de id;
	   		 PreparedStatement statement3 = cnx.prepareStatement(TROUVE_INFORMATION_LIVRE);
	   		 statement3.setString(1,String.valueOf(idLivre));
	   	     ResultSet resultSet3 = statement3.executeQuery();
	   	     String title = null;
		   	 int nbrExmplaire =0;
		   	 
		   	 while (resultSet3.next()) {
		   		 title=resultSet3.getString("titre");
		   		 nbrExmplaire=resultSet3.getInt("nbExemplaires"); 
		   		
		   	 }
		  //
   		  
   	      dateReservation=resultSet.getDate("date");
   	      accepté=resultSet.getInt("accepté");
   	      dateAcceptaionOuRefusé=resultSet.getDate("DateAcceptéOuRefusé");
   	      acceptéReservation=true?accepté==1:false;
   	      
   	      //System.out.println(email+" "+nom+" "+prenom+" "+username+" "+title+" "+nbrExmplaire+" "+dateReservation+" "+accepté+"\n");
   	      
   	      reservation reservation = new reservation(email,nom,prenom,username,title,Integer.valueOf(nbrExmplaire),dateReservation,acceptéReservation,dateAcceptaionOuRefusé);
   	      reservationList.add(reservation);
	   	   
   	      
    	  }
		return reservationList;
   	  	 
    	 
    	
    	
    	
    	
    }
  
    public int getIdLivre(String titre)  throws SQLException{
	   var cnx =dbConnexion.getConnection();
	   PreparedStatement statement = cnx.prepareStatement(TROUVE_ID_LIVRE);
  	   statement.setString(1,titre);
  	   ResultSet resultSet = statement.executeQuery();
  	   int idLivre = 0;
  	   while (resultSet.next()) {
  	   idLivre=resultSet.getInt("id");	
	  		 
	  		 
	  	 }
	  	
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
    public void addReservarion(int idLivre,int idUtilisateur,String dateStr)throws SQLException, ParseException{
    	var cnx =dbConnexion.getConnection();
    	

    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	java.util.Date utilDate = dateFormat.parse(dateStr);
    	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    	


    	PreparedStatement statement = cnx.prepareStatement(ADD_RESERVATION);
    	statement.setInt(1, idUtilisateur);
    	statement.setInt(2, idLivre);
    	statement.setDate(3,sqlDate);
    	System.out.println("seccuss");
    	statement.execute();
    	
    }
    	
    public void updateNbrReservation(int idUtilisateur)throws SQLException {
    	var cnx =dbConnexion.getConnection();
    	 PreparedStatement statement = cnx.prepareStatement(UPDATE_NBR_RESERVATION);
    	 statement.setInt(1,idUtilisateur); 
    	 statement.execute();
    }
	
    
    
    public Boolean UpdateReservation(int idUtilisateur, int idLivre, Date dateReservation ,String accepté)throws SQLException, ParseException {
    	
    		var cnx =dbConnexion.getConnection();
    		try {
   	       PreparedStatement statement = cnx.prepareStatement(UPDATE_RESERVATION);
   	       statement.setString(1,accepté);
   	       statement.setInt(2,idUtilisateur);
     	   statement.setInt(3,idLivre);
     	   statement.setDate(4,(java.sql.Date) dateReservation);
     	   
   	       int rowsUpdated = statement.executeUpdate();
     	   return true;
		} catch (Exception e) {
		   return false;
		}
    	   
    	  
		
	}
	public void supprimerReservation(int idUtilisateur, int idLivre, Date dateReservation) throws SQLException {
		var cnx =dbConnexion.getConnection();
		PreparedStatement statement = cnx.prepareStatement(DELETE_RESERVATION);
		statement.setInt(1,idUtilisateur);
  	   	statement.setInt(2,idLivre);
  	   	statement.setDate(3,(java.sql.Date) dateReservation);
		statement.executeUpdate();
	}
	
	}