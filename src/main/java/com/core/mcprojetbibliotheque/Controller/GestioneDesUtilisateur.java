package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GestioneDesUtilisateur implements Initializable {
	@FXML
	TextField seachUtilisateur;
	
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
	
	private WindowEffect effect;
 	@FXML
    private AnchorPane main;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			seachUtilisateur.setPromptText("Search : email, nom, prenom, categorie");
			effect=new WindowEffect(main);
			showUtilisateur();
			
			
			seachUtilisateur.textProperty().addListener((ObservableList,oldValue,newValue)->{
				
					try {
						searchUtilisateur();
					} catch (Exception e) {
						e.printStackTrace();
					}
			
			});
			
			
			
			
			
		} catch (Exception e) {
			
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
			e.printStackTrace();
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
			
			if(selectedUtililisateur.getCategorie().equals("Enseignant")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("error ");
				alert.setHeaderText("vous avez selectionnez un enseignant");
				
				
				
				
			}	else {
			
			
			
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
		
	}
	public void annulerPayement() throws Exception {
		try {
			ConnectionService cs = new ConnectionService();
			SelectionModel<Utilisateur> selectionModel = listUtilisateurTableView.getSelectionModel();
			Utilisateur selectedUtililisateur = selectionModel.getSelectedItem();			
			if(selectedUtililisateur != null) {
				
				
			if(selectedUtililisateur.getCategorie().equals("Enseignant")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("error ");
				alert.setHeaderText("vous avez selectionnez un enseignant");
				
				
				
				
			}	else {
				
				
				
				LocalDate date = null;

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
		}
			} catch (Exception e) {
				e.printStackTrace();
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

	
	
	public void searchUtilisateur() throws Exception {
		
		ConnectionService cs = new ConnectionService();
		ObservableList<Utilisateur> list =cs.getAllUtilisateur();
		ObservableList<Utilisateur> serachList =FXCollections.observableArrayList();
		for (Utilisateur utilisateur : list) {
			if(utilisateur.getEmail().toLowerCase().contains(seachUtilisateur.getText().toLowerCase()) ||
					utilisateur.getNom().toLowerCase().contains(seachUtilisateur.getText().toLowerCase()) ||
					utilisateur.getPrenom().toLowerCase().contains(seachUtilisateur.getText().toLowerCase())|| 
					utilisateur.getCategorie().toLowerCase().contains(seachUtilisateur.getText().toLowerCase()) 
					) 
	 {
				
				serachList.add(utilisateur);
				
				
				
			}
		}
		listUtilisateurTableView.setItems(serachList);
		
		
		
	}
	
	 public void exit(ActionEvent e) {
	        effect.exit(e);
	    }
	    public void dragged(MouseEvent e) {
	        effect.dragged(e);
	    }
	    public void presse(MouseEvent e) {
	        effect.pressed(e);
	    }
	    public void cache(ActionEvent e) {
	        effect.cache(e);
	    }
	    public void back(ActionEvent e) throws Exception {
	        effect.switchStage(e,"GestionaireDashboard.fxml");
	    }
	
	
	
	
	
	
	

}
