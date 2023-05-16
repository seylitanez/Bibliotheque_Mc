package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.UtilisateurConnecté;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.EmpruntService;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class empruntList implements Initializable{
	
	
		private WindowEffect effect;
		@FXML
	    private AnchorPane main;
	
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

					ObservableList<EmpruntLivre> empruntsFiltred2 = FXCollections.observableArrayList();

					for (EmpruntLivre emprunt : list) {
						if(emprunt.getEmail().equals(UtilisateurConnecté.email)) {
							empruntFiltered.add(emprunt);	
						}
						 empruntsFiltred2 = empruntFiltered
							    .filtered(e -> e.isDemandeProlonger() == false && e.getDateRestitution() == null && e.isEnRetard() == false && e.isProlongéRufusé()==false);
							    
					   
						
						
						
						
						
						
						
					}
				   titre.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("titre"));
				   auteur.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("auteur"));
				   DernierDelais.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,LocalDate>("Delais"));
				   demandeProlongé.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("demandeProlonger"));
				   prolongé.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("prolonge"));
				   enRetard.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("enRetard"));
				   abonneEmpruntTableView.setItems(empruntsFiltred2);

			} catch (Exception e) {
				System.out.println(e.getMessage()+" emprunt");
			}
		   }



		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try {
				showAbooneEmprunt();
				effect=new WindowEffect(main);
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
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText(null);
					alert.setContentText("Vous ne pouvez pas faire cette demande");

					alert.showAndWait();
						
					
					
					
					
					
					
					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage() +" demande");
			}
			
			
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
		        effect.switchStage(e,"login.fxml");
		    }
		
		
		
		
		
		

}
