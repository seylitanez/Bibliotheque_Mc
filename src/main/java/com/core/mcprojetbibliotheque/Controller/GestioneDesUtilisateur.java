package com.core.mcprojetbibliotheque.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Service.ConnectionService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestioneDesUtilisateur implements Initializable {
	@FXML
    private TableView<Utilisateur>listUtilisateurTableView;
    @FXML
	public TableColumn<Utilisateur, String>email;
	@FXML
	public TableColumn<Utilisateur, String>nom;
	@FXML
	public TableColumn<Utilisateur, String>prenom;
	@FXML
	public TableColumn<Utilisateur, String>categorie;
	@FXML
	public TableColumn<Utilisateur, Boolean>payement;
	@FXML
	public TableColumn<Utilisateur, Boolean>penalisé;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			showUtilisateur();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showUtilisateur() throws Exception {
		try {
			ConnectionService cs = new ConnectionService();
			ObservableList<Utilisateur> list = cs.getAllUtilisateur();
			for(Utilisateur utilisateur :list) {
				
				utilisateur.setPayement(cs.checkPayement(utilisateur.getEmail())); 

				utilisateur.setPenalisé(cs.checkPenalisation(utilisateur.getEmail())); 

				
				
				
			}
			
			
			
			
			 email.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
			 nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
			 prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
			 categorie.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("categorie"));
			 payement.setCellValueFactory(new PropertyValueFactory<Utilisateur,Boolean>("payement"));
			 penalisé.setCellValueFactory(new PropertyValueFactory<Utilisateur,Boolean>("penalisé"));
			 listUtilisateurTableView.setItems(list);
			
		} catch (Exception e) {
			System.out.println(e.getMessage() +"1");
		}
		
		
		
	}
	public void supprimer() {
				
		
		
		
	}
	public void payement() {
		
	}
	public void annulerPayement() {
	
}
	public void penaliser() {
	
}
	public void annulerPenalisation() {
	
}
	
	
	
}
