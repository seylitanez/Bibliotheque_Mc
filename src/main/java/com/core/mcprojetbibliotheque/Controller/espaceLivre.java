package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class espaceLivre implements Initializable{
	@FXML 
	TableView livreTabView;
	@FXML
	private Label label;
	@FXML
	public TableColumn<Livre, String>titre;
	@FXML
	public TableColumn<Livre, String>auteur;
	@FXML
	public TableColumn<Livre, Integer>nbrExemplaire;
	@FXML
	public TableColumn<Livre, Integer>codeRayon;
	@FXML
	public TableColumn<Livre, Integer>idLivre;
	@FXML
	public TableColumn<Livre, String>filiere;
	@FXML
	public TableColumn<Livre, Date>dateAjouter;
	@FXML
	public TextField titreTextField;
	@FXML
	public TextField auteurTextField;
	@FXML
	public TextField nbrExemplaireTextField;
	@FXML
	public TextField codeRayonTextField;
	@FXML
	public TextField filiereTextField;
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			showLivre();
			livreTabView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		        // Mettre à jour les TextField avec les informations du livre sélectionné
		        updateSelectedBook();
		    });
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showLivre() throws Exception {
		LivreService ls = new LivreService();
		ObservableList<Livre> list =ls.getAllLivres();
		titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
		auteur.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteur"));
		nbrExemplaire.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("nbExemplaires"));
		codeRayon.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("codeRayon"));
		idLivre.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("idLivre"));
		filiere.setCellValueFactory(new PropertyValueFactory<Livre,String>("filiere"));
		dateAjouter.setCellValueFactory(new PropertyValueFactory<Livre,Date>("dateAjouter"));
		livreTabView.setItems(list);
	}
	
	
	
	public void ajouterLivre() throws Exception{
			LivreService ls = new LivreService();
		
			Boolean bool=ls.addLivre(titreTextField.getText(),auteurTextField.getText(),nbrExemplaireTextField.getText(),codeRayonTextField.getText(),filiereTextField.getText());
			titreTextField.setText("");
			auteurTextField.setText("");
			nbrExemplaireTextField.setText("");
			codeRayonTextField.setText("");
			filiereTextField.setText("");
			showLivre();
			if (bool) {	
					//
					
				}else {
				}
			
			
			
			
					
		
		
		
	}
	
	public void modifierLivre() throws Exception{
		LivreService ls = new LivreService();
		TableViewSelectionModel<Livre> selectionModel = livreTabView.getSelectionModel();
		Livre livreSelectionne = selectionModel.getSelectedItem();
		int id = livreSelectionne.getIdLivre();
		Boolean bool=ls.UpdateLivre(id,titreTextField.getText(),auteurTextField.getText(),nbrExemplaireTextField.getText(),codeRayonTextField.getText(),null,filiereTextField.getText());
		showLivre();
		titreTextField.setText("");
		auteurTextField.setText("");
		nbrExemplaireTextField.setText("");
		codeRayonTextField.setText("");
		filiereTextField.setText("");
		
	}
	public void supprimerLivre() throws Exception{
		
		LivreService ls = new LivreService();
		TableViewSelectionModel<Livre> selectionModel = livreTabView.getSelectionModel();
		Livre livreSelectionne = selectionModel.getSelectedItem();
		int id = livreSelectionne.getIdLivre();
		ls.SupprimerLivre(id);
		showLivre();
		titreTextField.setText("");
		auteurTextField.setText("");
		nbrExemplaireTextField.setText("");
		codeRayonTextField.setText("");
		filiereTextField.setText("");
	}
	
	public Livre updateSelectedBook() {
	   
	    TableViewSelectionModel<Livre> selectionModel = livreTabView.getSelectionModel();
	    Livre livreSelectionne = selectionModel.getSelectedItem();

	    if (livreSelectionne != null) {
	   
	        titreTextField.setText( livreSelectionne.getTitre());
	        auteurTextField.setText(livreSelectionne.getAuteur());
	        nbrExemplaireTextField.setText(String.valueOf(livreSelectionne.getNbExemplaires()));
	        codeRayonTextField.setText(String.valueOf(livreSelectionne.getCodeRayon()));
	        filiereTextField.setText(livreSelectionne.getFiliere());
	       
	       
	        

	       
	        
	    }
		return livreSelectionne;
	}

}
