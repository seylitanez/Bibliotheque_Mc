package com.core.mcprojetbibliotheque.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.UtilisateurConnecté;

//import org.jetbrains.annotations.ApiStatus.AvailableSince;

import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class reservattionController implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			//showListResvation();
			showListResvationAccepté();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	@FXML
	public Button AccepterResvation;
	@FXML
	public Button AjouterDansEmpurnt;
	@FXML
	public Button Supprimer;
	@FXML
	public Button RefuserReservation;
	private WindowEffect eff;
	@FXML
	public TableView reservationTable;
	
	@FXML
	public TableColumn<reservation, String>email;
	@FXML
	public TableColumn<reservation, String>nom;
	@FXML
	public TableColumn<reservation, String>prenom;
	@FXML
	public TableColumn<reservation, String>username;
	
	@FXML
	public TableColumn<reservation, String>title;
	@FXML
	public TableColumn<reservation, Integer>nbrExemplaire;
	@FXML
	public TableColumn<reservation, Date>dateReservation;
	@FXML
	public TableColumn<reservation, Boolean>accepté;
	@FXML
	public TableColumn<reservation, Date>dateAcceptaion;
	@FXML
	public Label labelType;

	public void completéTableau() {
		email.setCellValueFactory(new PropertyValueFactory<reservation,String>("email"));
		nom.setCellValueFactory(new PropertyValueFactory<reservation,String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<reservation,String>("prenom"));
		username.setCellValueFactory(new PropertyValueFactory<reservation,String>("userName"));
		title.setCellValueFactory(new PropertyValueFactory<reservation,String>("title"));
		nbrExemplaire.setCellValueFactory(new PropertyValueFactory<reservation,Integer>("nbrExemplaire"));
		dateReservation.setCellValueFactory(new PropertyValueFactory<reservation,Date>("dateReservation"));
		accepté.setCellValueFactory(new PropertyValueFactory<reservation,Boolean>("accepté"));
		dateAcceptaion.setCellValueFactory(new PropertyValueFactory<reservation,Date>("dateAcceptaionOuRefusé"));
	}
	
	@FXML
	public void showListResvation() throws Exception {
		ConnectionService cs = new ConnectionService();
		ObservableList<reservation> list =cs.getReservationList("0");
		completéTableau();
		reservationTable.setItems(list);
		labelType.setText("Liste Des  Resrvation");
		AccepterResvation.setVisible(true);
		RefuserReservation.setVisible(true);
		AjouterDansEmpurnt.setVisible(false);
		Supprimer.setVisible(false);
			
	}
	
	public void showListResvationAccepté() throws Exception {
		ConnectionService cs = new ConnectionService();
		ObservableList<reservation> list =cs.getReservationList("1");
		completéTableau();
		reservationTable.setItems(list);
		labelType.setText("Liste Des  Resrvation Accepté");	
		AccepterResvation.setVisible(false);
		RefuserReservation.setVisible(false);
		AjouterDansEmpurnt.setVisible(true);
		Supprimer.setVisible(true); 
		
	}
	public void showListResvationRefusé() throws Exception {
		ConnectionService cs = new ConnectionService();
		ObservableList<reservation> list =cs.getReservationList("2");
		completéTableau();
		reservationTable.setItems(list);
		labelType.setText("Liste Des  Resrvation Refusé");
		AccepterResvation.setVisible(false);
		RefuserReservation.setVisible(false);
		AjouterDansEmpurnt.setVisible(false);
		Supprimer.setVisible(false);
			
	}
	
	
	
	public void AccepterResvation() throws Exception {
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		if (selectedReservation != null) {
			
		   String email=selectedReservation.getEmail();
		   String titre =selectedReservation.getTitle();
		   Date dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(titre);
		   int idUtilisateur=cs.getIdUtilisateur(email);
		   
		  Boolean bool =cs.UpdateReservation(idUtilisateur,idLivre,dateReservation,"1");
		  
		    
		}
		showListResvation();
	}
	public void RefuserReservation() throws Exception {
		
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		if (selectedReservation != null) {
			
		   String email=selectedReservation.getEmail();
		   String titre =selectedReservation.getTitle();
		   Date dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(titre);
		   int idUtilisateur=cs.getIdUtilisateur(email);
		   
		  Boolean bool =cs.UpdateReservation(idUtilisateur,idLivre,dateReservation,"2");
		  
		    
		}
		
		
		
		showListResvation();	
	}
	public void AjouterDansEmpurnt() throws Exception {
		Supprimer();
		//ajouté a emprunt
		
	}
	public void Supprimer() throws Exception {
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		if (selectedReservation != null) {
			
		   String email=selectedReservation.getEmail();
		   String titre =selectedReservation.getTitle();
		   Date dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(titre);
		   int idUtilisateur=cs.getIdUtilisateur(email);
		   
		   cs.supprimerReservation(idUtilisateur,idLivre,dateReservation);
		   
	}
		showListResvationAccepté();
		
		
	}
	
	 public void exit(ActionEvent e) {
	        eff.exit(e);
	    }
	   
	    
	    public void cache(ActionEvent e) {
	        eff.cache(e);
	    }



	
	  
	
	
	
	

}
