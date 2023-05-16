package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.io.IOExceptionList;


public class DemandeProlongé implements Initializable{
	@FXML
    private TableView<EmpruntLivre>demandeProlongerTableView;
    @FXML
	public TableColumn<EmpruntLivre, String>email;
	@FXML
	public TableColumn<EmpruntLivre, String>nom;
	@FXML
	public TableColumn<EmpruntLivre, String>prenom;
	@FXML
	public TableColumn<EmpruntLivre, String>titre;
	@FXML
	public TableColumn<EmpruntLivre, Integer>nbrExemplaire;
	@FXML
	public TableColumn<EmpruntLivre, LocalDate>Delais;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			showDemande();
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void showDemande() throws  SQLException, IOException {
		LivreService ls = new LivreService();
		   ObservableList<EmpruntLivre> list = ls.getEmprunt();
		   for (EmpruntLivre emprunt : list) {
			   emprunt.dernierDelais();
		   }
		   // filtrer les emprunt 
		   ObservableList<EmpruntLivre> empruntsFiltres = list
				    .filtered(e -> e.isDemandeProlonger() && e.getDateRestitution() == null && e.isEnRetard() == false && e.isProlongéRufusé()==false);
				    
		   
		   
		   
		   
		   
		   email.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("email"));
		   nom.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("nom"));
		   prenom.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("prenom"));
		   titre.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("titre"));
		   nbrExemplaire.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Integer>("nbrExemplaire"));
		   Delais.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,LocalDate>("Delais"));
		   demandeProlongerTableView.setItems(list);
	
	}



	public void accepter() throws IOException, SQLException {
		
		
		SelectionModel<EmpruntLivre> selectionModel = demandeProlongerTableView.getSelectionModel();
		EmpruntLivre selectedEmprunt = selectionModel.getSelectedItem();
		if(selectedEmprunt != null) {
			
			LivreService ls =new  LivreService();
			ls.accepterDemande(selectedEmprunt.getIdEmprunt());
			
			SendEmail sendEmail = new SendEmail();
			sendEmail.sendEmailMethode(selectedEmprunt.getEmail(),"Demande de Prolongé est accepté", "le dernier delais pour restituer livre "+selectedEmprunt.getTitre()+" sera "+selectedEmprunt.getDelais());
			showDemande();
		}
		
		
		 
		
		
	}
	public void refuser() throws IOException, SQLException {
		SelectionModel<EmpruntLivre> selectionModel = demandeProlongerTableView.getSelectionModel();
		EmpruntLivre selectedEmprunt = selectionModel.getSelectedItem();
		if(selectedEmprunt != null) {
			
			LivreService ls =new  LivreService();
			ls.ReffuserDemande(selectedEmprunt.getIdEmprunt());
			SendEmail sendEmail = new SendEmail();
			sendEmail.sendEmailMethode(selectedEmprunt.getEmail()," Demande de Prolongé est reffusé","Votre deamande de prolonge le delais de restitution le livre "+selectedEmprunt.getTitre()+" est reffusé \n vous devez restituer le livre avant "+selectedEmprunt.getDelais() );
			showDemande();
		}
	}
	
}
