package com.core.mcprojetbibliotheque.Controller;

import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class reservattionController implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			showListResvation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
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
	public TableColumn<reservation, Date>date;
	@FXML
	public TableColumn<reservation, Boolean>accepté;
	
	
	
	@FXML
	public void showListResvation() throws Exception {
		ConnectionService cs = new ConnectionService();
		ObservableList<reservation> list =cs.getReservationList();
		email.setCellValueFactory(new PropertyValueFactory<reservation,String>("email"));
		nom.setCellValueFactory(new PropertyValueFactory<reservation,String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<reservation,String>("prenom"));
		username.setCellValueFactory(new PropertyValueFactory<reservation,String>("userName"));
		title.setCellValueFactory(new PropertyValueFactory<reservation,String>("title"));
		nbrExemplaire.setCellValueFactory(new PropertyValueFactory<reservation,Integer>("nbrExemplaire"));
		date.setCellValueFactory(new PropertyValueFactory<reservation,Date>("dateReservation"));
		accepté.setCellValueFactory(new PropertyValueFactory<reservation,Boolean>("accepté"));
		reservationTable.setItems(list);
		
	}
	
	 public void exit(ActionEvent e) {
	        eff.exit(e);
	    }
	   
	    
	    public void cache(ActionEvent e) {
	        eff.cache(e);
	    }



	
	  
	
	
	
	

}
