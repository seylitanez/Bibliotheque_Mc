package com.core.mcprojetbibliotheque.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.UtilisateurConnecté;

//import org.jetbrains.annotations.ApiStatus.AvailableSince;

import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

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
	public TableColumn<reservation, String>title;
	@FXML
	public TableColumn<reservation, String>auteur;
	@FXML
	public TableColumn<reservation, Integer>nbrExemplaire;
	@FXML
	public TableColumn<reservation, Date>dateReservation;
	@FXML
	public TableColumn<reservation, Boolean>EnRetard;
	@FXML
	public Label labelType;

	public void completéTableau() {
		email.setCellValueFactory(new PropertyValueFactory<reservation,String>("email"));
		nom.setCellValueFactory(new PropertyValueFactory<reservation,String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<reservation,String>("prenom"));	
		title.setCellValueFactory(new PropertyValueFactory<reservation,String>("title"));
		auteur.setCellValueFactory(new PropertyValueFactory<reservation,String>("auteur"));
		nbrExemplaire.setCellValueFactory(new PropertyValueFactory<reservation,Integer>("nbrExemplaire"));
		dateReservation.setCellValueFactory(new PropertyValueFactory<reservation,Date>("dateReservation"));
		EnRetard.setCellValueFactory(new PropertyValueFactory<reservation,Boolean>("estEnRetard"));
		
		//dateAcceptaion.setCellValueFactory(new PropertyValueFactory<reservation,Date>("dateAcceptaionOuRefusé"));
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
		for (reservation reservation : list) {
			reservation.EstEnRetard();
		}
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
		   String auteur=selectedReservation.getEmail();
				
		   LocalDate dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(titre,auteur);// car deux livre ne peuvent pas avoir le meme titre at auteur
		   int idUtilisateur=cs.getIdUtilisateur(email);
		   
		   Boolean bool =cs.UpdateReservation(idUtilisateur,idLivre,dateReservation,"1");
		   cs.decrementerNombreExemplaire(idLivre);
		   //Pour email
		   SendEmail sendEmail = new SendEmail();
		   LocalDate date = LocalDate.now().plusWeeks(1);
		   String subject = "Reservation Accepté";
		   String text ="la reservation de livre "+titre.toUpperCase()+"est accepté\n vous devez prendre votre livre avant"+date;
		   sendEmail.sendEmailMethode(email,subject,text);
		  
		    
		}
		showListResvation();
	}
	public void RefuserReservation() throws Exception {
		
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		if (selectedReservation != null) {
		
		   LocalDate dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(selectedReservation.getTitle(),selectedReservation.getAuteur());
		   int idUtilisateur=cs.getIdUtilisateur(selectedReservation.getEmail());
 
		   Boolean bool =cs.UpdateReservation(idUtilisateur,idLivre,dateReservation,"2");
		   SendEmail sendEmail = new SendEmail();
		   LocalDate date = LocalDate.now().plusWeeks(1);
		   String subject = "Reservation Refusé";
		   String text ="la reservation de livre "+selectedReservation.getTitle().toUpperCase()+"est refusé";
		   sendEmail.sendEmailMethode(selectedReservation.getEmail(),subject,text);
		    
		}
		
		
		
		showListResvation();	
	}
	public void AjouterDansEmpurnt() throws Exception {
		Supprimer();
		
		
	}
	public void Supprimer() throws Exception {
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		if (selectedReservation != null) {
			
		   
		   LocalDate dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(selectedReservation.getTitle(),selectedReservation.getAuteur());
		   int idUtilisateur=cs.getIdUtilisateur(selectedReservation.getEmail());
		   
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
