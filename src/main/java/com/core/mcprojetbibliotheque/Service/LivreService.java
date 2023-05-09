package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;

import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Utils.Constantes;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

public class LivreService {

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
    
    
    
    public Boolean checkIfTitleExist(String titre) throws SQLException {
    	
    	var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(CHERCHER_TITLE_LIVRE);
        preparedStatement.setString(1,titre);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int nombreLivres = resultSet.getInt(1);
            return nombreLivres > 0;
        }
        return false;
        
        
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
}
