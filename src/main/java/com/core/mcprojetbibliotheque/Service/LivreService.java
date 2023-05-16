package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Utils.Constantes;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

public class LivreService {

    private static final Date NULL = null;
	private DbConnexion dbConnexion;

    public LivreService() throws IOException {
        dbConnexion=new DbConnexion();

    }

    
    public ObservableList<Livre> getAllLivres() throws SQLException, ClassNotFoundException, IOException {
        var connection= dbConnexion.getConnection();

        var preparedStatement=connection.prepareStatement(LISTER_LIVRES);
        ResultSet resultSet = preparedStatement.executeQuery();
       // ArrayList list=new ArrayList<>();
        ObservableList<Livre> list = FXCollections.observableArrayList();

        while (resultSet.next()){
            list.add(new Livre(resultSet.getInt("id"),resultSet.getString("titre"),resultSet.getString("auteur"),resultSet.getInt("nbExemplaires"),resultSet.getInt("codeRayon"),resultSet.getString("photo"),resultSet.getString("filiere"),resultSet.getDate("dateAjout")));
        }
        			

        return  list;
    }

    public ObservableList<Livre> searchLivres(String motCle) throws SQLException, ClassNotFoundException {
        var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(CHERCHER_LIVRE);
        preparedStatement.setString(1,"%"+motCle+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        //ArrayList list=new ArrayList<>();
        ObservableList<Livre> list = FXCollections.observableArrayList();
        while (resultSet.next()){
            list.add(new Livre(resultSet.getInt("id"),resultSet.getString("titre"),resultSet.getString("auteur"),resultSet.getInt("nbExemplaires"),resultSet.getInt("codeRayon"),resultSet.getString("photo"),resultSet.getString("filiere"),resultSet.getDate("dateAjout")));
        }

        return  list;
    }
    
    
    
    public Boolean checkIfTitleAndAuteurExist(String titre,String auteur) throws SQLException {
    	try {
			

        	var connection= dbConnexion.getConnection();
            var preparedStatement=connection.prepareStatement(CHERCHER_TITLE_LIVRE);
            preparedStatement.setString(1,titre);
            preparedStatement.setString(2,auteur);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
                return true;
                
            }
            return false;
    		
    		
    		
    		
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return false;
		}
        
        
    }

	public Boolean addLivre(String titre, String auteur, String nbrExemplaire, String codeRayon,String filiere) throws SQLException {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(ADD_LIVRE,Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1,titre);
	        preparedStatement.setString(2, auteur);
	        int  nbExemp=Integer.parseInt(nbrExemplaire);
	        int codeR=Integer.parseInt(codeRayon);
	        preparedStatement.setInt(3, nbExemp);
	        preparedStatement.setInt(4, codeR);
	        preparedStatement.setString(5,filiere);
	        int rows = preparedStatement.executeUpdate();
	        ResultSet rs = preparedStatement.getGeneratedKeys();
	     // Vérification du nombre de lignes affectées
	     if (rows > 0) {
	         return true;
	     } else {
	         return false;
			
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	public Boolean UpdateLivre(int id, String titre, String auteur, String nbrExemplaire, String codeRayon, String photo,String filiere) throws SQLException {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(UPDATE_LIVRE);
	        preparedStatement.setString(1,titre);
	        preparedStatement.setString(2, auteur);
	        int  nbExemp=Integer.parseInt(nbrExemplaire);
	        int codeR=Integer.parseInt(codeRayon);
	        preparedStatement.setInt(3, nbExemp);
	        preparedStatement.setInt(4, codeR);
	        preparedStatement.setString(5,photo);
	        preparedStatement.setString(6,filiere);
	        preparedStatement.setInt(7,id);
	        int rows = preparedStatement.executeUpdate();
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public void SupprimerLivre(int id)throws SQLException {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(SUPPRIMER_LIVRE);
	        preparedStatement.setInt(1,id);
	        int rows = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	

	public Boolean ajouterExemplaire(int idLivre, String nbrExemplaire) {
		try {
			var connection= dbConnexion.getConnection();
	        var preparedStatement=connection.prepareStatement(AJOUTEREXEMPLAIRE);
	        preparedStatement.setInt(1,Integer.parseInt(nbrExemplaire));
	        preparedStatement.setInt(2,idLivre);
	        preparedStatement.executeUpdate();
	        return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	


	public ObservableList<EmpruntLivre> getEmprunt() throws SQLException {
		
			
				 var connection= dbConnexion.getConnection();
			        var preparedStatement=connection.prepareStatement(LISTER_EMPRUNT);
			        ObservableList<EmpruntLivre> list = FXCollections.observableArrayList();
			        ResultSet resultSet = preparedStatement.executeQuery();
					   
			        while (resultSet.next()){
			        	 //pour trouver les information d utilisateur aprtir de id;
				   		 PreparedStatement statement2 = connection.prepareStatement(TROUVE_INFORMATION_UTILISATEUR);
				   		 statement2.setString(1,String.valueOf(resultSet.getInt("idUtilisateur")));
				   	     ResultSet resultSet2 = statement2.executeQuery();
				   	     String email=null;
				   	     String nom = null;
				   	     String prenom=null;
				   	     	while (resultSet2.next()) {
						   		  email=resultSet2.getString("email");
						   		  nom=resultSet2.getString("nom"); 
						   		  prenom=resultSet2.getString("prenom");
						   		
				   	     	}	
						  //pour trouver les information d livre aprtir de id;
				 		 PreparedStatement statement3 = connection.prepareStatement(TROUVE_INFORMATION_LIVRE);
				   		 statement3.setString(1,String.valueOf(resultSet.getInt("idLivre")));
				   		 ResultSet resultSet3 = statement3.executeQuery();
				   		 	  String auteur=null;
				   		 	  String title = null;
				   		 	  int nbrExemplaire=0;
						   	 while (resultSet3.next()) {
						   		 title=resultSet3.getString("titre");
						   		 auteur=resultSet3.getString("auteur");  
						   		 nbrExemplaire =resultSet3.getInt("nbExemplaires");
					   	 }
						   	
						   	Boolean demandeProlongé= false;
						   	Boolean prolongé =false;
						   	Boolean prolongéRufusé = false;
						   	Boolean penalisé = false;
						   	if(resultSet.getInt("demandeProlonger")==1) {
						   		demandeProlongé = true;
						   	}	
						   	if(resultSet.getInt("prolonge")==1) {
						   		prolongé=true;
						   	}
						   	if(resultSet.getInt("prolongéRufusé")==1) {
						   		prolongéRufusé = true;
						   	}if(resultSet.getInt("penalisé")==1) {
						   		penalisé = true;
						   	}
						   	
						   	
						   	if(resultSet.getDate("dateRestitution") != null) {
						          EmpruntLivre emprunt = new EmpruntLivre(resultSet.getInt("idEmprunt"),email,nom,prenom,title,auteur,nbrExemplaire,resultSet.getDate("dateEmprunt").toLocalDate(),resultSet.getDate("dateRestitution").toLocalDate(),demandeProlongé,prolongé,prolongéRufusé,false,null,penalisé);
						          list.add(emprunt);
						   	}else {
						          EmpruntLivre emprunt = new EmpruntLivre(resultSet.getInt("idEmprunt"),email,nom,prenom,title,auteur,nbrExemplaire,resultSet.getDate("dateEmprunt").toLocalDate(),null,demandeProlongé,prolongé,prolongéRufusé,false,null,penalisé);
						          list.add(emprunt);
						   	}
						   	
						   	
						   	
						   	
			          
			         
			         
			         
			        }
			
			        return list;
			
		
	}


	public void updateRestitution(int idEmprunt) throws SQLException {
		var cnx= dbConnexion.getConnection();
		PreparedStatement statement = cnx.prepareStatement(UPDATE_EMPRUNT_RESTITUTION);
		statement.setInt(1, idEmprunt);
		int rowsUpdated = statement.executeUpdate();
		
	}


	public void updateExemplaireLivre(String titre,String auteur) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(UPDATE_EXEMPLAIRE_NEMBER);
        preparedStatement.setString(1,titre);
        preparedStatement.setString(2,auteur);
        preparedStatement.executeUpdate();
		
	}


	public void decrementerNombreEmprintPourUtilisateur(String email) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(DECREMENTER_NOMBRE_EMPRUNT);
        preparedStatement.setString(1,email);
        preparedStatement.executeUpdate();
	}
	
	
	
	public void addDemande( int idEmprunt) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(ADD_DEMANDE);
        preparedStatement.setInt(1,idEmprunt);
        preparedStatement.executeUpdate();
		
		
	}


	public void accepterDemande(int idEmprunt) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(ACCEPTER_DEMANDE);
        preparedStatement.setInt(1,idEmprunt);
        preparedStatement.executeUpdate();
		
	}


	public void ReffuserDemande(int idEmprunt) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(REFFUSER_DEMANDE);
        preparedStatement.setInt(1,idEmprunt);
        preparedStatement.executeUpdate();
		
	}


	public void addPenalisation(int idEmprunt) throws SQLException {
		var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(ADD_PENALISATION);
        preparedStatement.setInt(1,idEmprunt);
        preparedStatement.executeUpdate();
		
	}
	
	
	
	
}
