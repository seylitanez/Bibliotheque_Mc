package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class empruntEnRetard implements Initializable {
		@FXML
		public TableView<EmpruntLivre> empruntEnRetardTableView;
	    @FXML
		public TableColumn<EmpruntLivre, String>email;
		@FXML
		public TableColumn<EmpruntLivre, String>nom;
		@FXML
		public TableColumn<EmpruntLivre, String>prenom;
		@FXML
		public TableColumn<EmpruntLivre, String>titre;
		@FXML
		public TableColumn<EmpruntLivre, LocalDate>dernierDelais;
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try {
				ShowEmpruntEnRetard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
		}
		public void ShowEmpruntEnRetard() throws Exception {
			
			 LivreService ls = new LivreService();
			  ObservableList<EmpruntLivre> list = ls.getEmprunt();
			  ObservableList<EmpruntLivre> empruntFiltered = FXCollections.observableArrayList();

			  for (EmpruntLivre emprunt : list) {
				     emprunt.dernierDelais();
				     emprunt.EstEnRetard();
					if(emprunt.getDateRestitution() == null && emprunt.isEnRetard() == true && emprunt.isPenalisé()==false) {
						
						empruntFiltered.add(emprunt);	
						
					}
					System.out.println(emprunt.getDateRestitution()+" "+emprunt.isEnRetard()+emprunt.getDelais());
						
					//}
			   
					   email.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("email"));
					   nom.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("nom"));
					   prenom.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("prenom"));
					   titre.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("titre"));
					   dernierDelais.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,LocalDate>("Delais"));
					   empruntEnRetardTableView.setItems(empruntFiltered);
		}
		
	    
		}
		public void Penaliser() throws Exception {
			SelectionModel<EmpruntLivre> selectionModel = empruntEnRetardTableView.getSelectionModel();
			EmpruntLivre selectedEmprunt = selectionModel.getSelectedItem();
			
			
			if(selectedEmprunt != null) {
				
				ConnectionService cs = new ConnectionService();
				cs.penaliserUtilisateur(selectedEmprunt.getEmail());
				LivreService ls = new LivreService();
				ls.addPenalisation(selectedEmprunt.getIdEmprunt());
				ShowEmpruntEnRetard();
				SendEmail sendEmail = new SendEmail();
				sendEmail.sendEmailMethode(selectedEmprunt.getEmail(),"Penalisation","vous etes penaliser jusque "+LocalDate.now().plusMonths(1)+"car vous n avez pas restituer le livre "+selectedEmprunt.getTitre());
			
			}
		}
		
}
