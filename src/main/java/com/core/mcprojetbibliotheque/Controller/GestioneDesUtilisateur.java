package com.core.mcprojetbibliotheque.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Service.ConnectionService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	public void supprimer() throws Exception {
		ConnectionService cs = new ConnectionService();
		SelectionModel<Utilisateur> selectionModel = listUtilisateurTableView.getSelectionModel();
		Utilisateur selectedUtililisateur = selectionModel.getSelectedItem();			
		if(selectedUtililisateur != null) {
			boolean resultat = cs.deleteUtilisateur(selectedUtililisateur.getEmail());
			if(resultat == true ) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Utilisateur supprimé");
				alert.setHeaderText(null);
				alert.setContentText("L'utilisateur "+selectedUtililisateur.getNom()+" "+selectedUtililisateur.getPrenom()+" été supprimé avec succès.");

				alert.showAndWait();
			}
			showUtilisateur();
			
		}
		
		
	}
	public void payement() throws Exception {
		ConnectionService cs = new ConnectionService();
		SelectionModel<Utilisateur> selectionModel = listUtilisateurTableView.getSelectionModel();
		Utilisateur selectedUtililisateur = selectionModel.getSelectedItem();			
		if(selectedUtililisateur != null) {
			
			boolean resultat = cs.Payement(selectedUtililisateur.getEmail(),LocalDate.now().plusYears(1));
			if(resultat == true ) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Payement ");
				alert.setHeaderText(null);
				alert.setContentText("Le Payement est ajouté avec succès pour utilisateur "+selectedUtililisateur.getNom()+" "+selectedUtililisateur.getPrenom());

				alert.showAndWait();
		}
			showUtilisateur();
		
		}
		
	}
	public void annulerPayement() throws Exception {
		try {
			ConnectionService cs = new ConnectionService();
			SelectionModel<Utilisateur> selectionModel = listUtilisateurTableView.getSelectionModel();
			Utilisateur selectedUtililisateur = selectionModel.getSelectedItem();			
			if(selectedUtililisateur != null) {
				LocalDate date = LocalDate.of(2000, 1, 1);

				boolean resultat = cs.Payement(selectedUtililisateur.getEmail(),date);
				
				if(resultat == true ) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Payement ");
					alert.setHeaderText(null);
					alert.setContentText("Le Payement est anuule avec succès pour utilisateur "+selectedUtililisateur.getNom()+" "+selectedUtililisateur.getPrenom());

					alert.showAndWait();
					}
				showUtilisateur();
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
}
	public void penaliser() throws Exception {
		ConnectionService cs = new ConnectionService();
		SelectionModel<Utilisateur> selectionModel = listUtilisateurTableView.getSelectionModel();
		Utilisateur selectedUtililisateur = selectionModel.getSelectedItem();			
		if(selectedUtililisateur != null) {
			boolean resultat = cs.penaliserUtilisateur(selectedUtililisateur.getEmail());
			if(resultat == true ) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Penalisation ");
				alert.setHeaderText(null);
				alert.setContentText("Utilistaeur " + selectedUtililisateur.getNom()+" "+selectedUtililisateur.getPrenom()+" est penalisé.");

				alert.showAndWait();
				}
			showUtilisateur();
			}
			
			
		}

	public void annulerPenalisation() throws Exception {
		ConnectionService cs = new ConnectionService();
		SelectionModel<Utilisateur> selectionModel = listUtilisateurTableView.getSelectionModel();
		Utilisateur selectedUtililisateur = selectionModel.getSelectedItem();			
		if(selectedUtililisateur != null) {
			boolean resultat = cs.AnnulerpenalisationUtilisateur(selectedUtililisateur.getEmail());
			if(resultat == true ) {
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Penalisation ");
				alert.setHeaderText(null);
				alert.setContentText("penalisation est anuule avec succès pour utilisateur "+selectedUtililisateur.getNom()+" "+selectedUtililisateur.getPrenom());

				alert.showAndWait();
				}
			showUtilisateur();
				
				
				}
			}

	
	

}
