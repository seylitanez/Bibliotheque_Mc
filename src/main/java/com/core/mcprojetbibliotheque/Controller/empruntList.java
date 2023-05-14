package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.EmpruntService;
import com.core.mcprojetbibliotheque.Service.LivreService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class empruntList implements Initializable{
		@FXML
	    private TableView<EmpruntLivre>abonneEmpruntTableView;
	    @FXML
		public TableColumn<EmpruntLivre, String>titre;
		@FXML
		public TableColumn<EmpruntLivre, String>auteur;
		@FXML
		public TableColumn<EmpruntLivre, LocalDate>DernierDelais;
		@FXML  
		public TableColumn<EmpruntLivre, Boolean>demandeProlongé;
		@FXML  
		public TableColumn<EmpruntLivre, Boolean>prolongé;
		@FXML
		public TableColumn<EmpruntLivre, Boolean>enRetard;
		
		
		
		
		  public void showAbooneEmprunt() throws IOException, SQLException {
			   try {
				   LivreService ls = new LivreService();
				   ObservableList<EmpruntLivre> list = ls.getEmprunt();
					
					
					for (EmpruntLivre emprunt : list) {
						emprunt.EstEnRetard();
						emprunt.dernierDelais();
						
					}
					ObservableList<EmpruntLivre> empruntFiltered = FXCollections.observableArrayList();
					for (EmpruntLivre emprunt : list) {
						if(emprunt.getEmail().equals("lyes")) {
							empruntFiltered.add(emprunt);	
						}
						//TODO FILTRER LES EMPRUNT
						
						
						
						
						
						
						
					}
				   titre.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("titre"));
				   auteur.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("auteur"));
				   DernierDelais.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,LocalDate>("Delais"));
				   demandeProlongé.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("demandeProlonger"));
				   prolongé.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("prolonge"));
				   enRetard.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("enRetard"));
				   abonneEmpruntTableView.setItems(empruntFiltered);

			} catch (Exception e) {
				System.out.println(e.getMessage()+" emprunt");
			}
		   }



		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try {
				showAbooneEmprunt();
			} catch (IOException | SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		public void demandeProlongé() throws IOException, SQLException {
			try {
				SelectionModel<EmpruntLivre> selectionModel = abonneEmpruntTableView.getSelectionModel();
				EmpruntLivre selectedEmprunt = selectionModel.getSelectedItem();	
				if(selectedEmprunt != null && selectedEmprunt.isEnRetard() == false && selectedEmprunt.isDemandeProlonger() == false) {
					LivreService ls =new  LivreService();
					ls.addDemande(selectedEmprunt.getIdEmprunt());
					showAbooneEmprunt();
					
				}else {
					/*Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText(null);
					alert.setContentText("Vous ne pouvez pas faire cette demande");

					alert.showAndWait();
						*/
					TextInputDialog dialog = new TextInputDialog();
					dialog.setTitle("Dialog avec champ de texte");
					dialog.setHeaderText("Entrez votre texte:");
					dialog.setContentText("Texte:");

					// Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
					Optional<String> result = dialog.showAndWait();

					// Vérifier si l'utilisateur a cliqué sur OK
					if (result.isPresent()) {
					    String texte = result.get();
					    System.out.println("Le texte saisi est : " + texte);
					}
					
					
					
					
					
					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage() +" demande");
			}
			
			
		}

}
