package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class espaceLivre implements Initializable{
	@FXML 
	TableView livreTabView;
	private WindowEffect effect;
	@FXML
    private AnchorPane espaceLivre;
	@FXML
	private Label label;
	@FXML
	public TableColumn<Livre, String>titre;
	@FXML
	public TableColumn<Livre, String>auteurLivre;
	@FXML
	public TableColumn<Livre, Integer>nbrExemplaireLivre;
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
	@FXML
	public TextField ajouterExemplaireTextField;
	@FXML
	public TextField searchTextField;
	@FXML
	public TextField searchEmpruntTextField;
	@FXML
	public TextField searchReservationTextField;
	
	// for the table Emprunt 
	@FXML 
	TableView empruntTableView;
	
	@FXML
	public TableColumn<EmpruntLivre, String>emailEmprunt;
	@FXML
	public TableColumn<EmpruntLivre, String>nomEmprunt;
	@FXML
	public TableColumn<EmpruntLivre, String>prenomEmprunt;
	@FXML
	public TableColumn<EmpruntLivre, String>titreEmprunt;
	@FXML
	public TableColumn<EmpruntLivre, String>auteurEmprunt;
	@FXML
	public TableColumn<EmpruntLivre, Integer>numeroEmp;
	
	
	
	
	
	@FXML
	public ChoiceBox<String> reservationChoiceBox;
	ObservableList<String> options = FXCollections.observableArrayList(
		    "listeReservation", "listeReservationAccepté");
	
	
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
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		searchTextField.setPromptText("Search : titre, auteur, filiere");
		searchReservationTextField.setPromptText("Search : email, nom, prenom, titre  ,EnRetard");
		searchEmpruntTextField.setPromptText("Search : email, nom, prenom, titre ");
		
		
		
		
		
		try {
			effect=new WindowEffect(espaceLivre);
			showLivre();
			livreTabView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			        // Mettre à jour les TextField avec les informations du livre sélectionné
	       
			updateSelectedBook();
			});
			ShowEmpruntList();
			
			try {
				
				showListResvation();
			} catch (Exception e) {
				System.out.println(e.getMessage()+"1");
			}
			
			
			
			
			
			
			
			
			
			
			
			
			searchTextField.textProperty().addListener((ObservableList,oldValue,newValue)->{
				try {
					search();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			searchEmpruntTextField.textProperty().addListener((ObservableList,oldValue,newValue)->{
				try {
					searchEmprunt();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			
			searchReservationTextField.textProperty().addListener((ObservableList,oldValue,newValue)->{
				
					try {
						searchReservation();
					} catch (Exception e1) {
						  e1.printStackTrace();
					}
				
			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			//pour TabPane: 
			reservationChoiceBox.setItems(options);
			reservationChoiceBox.setValue("listeReservation");
			reservationChoiceBox.setOnAction(e -> {
			    String selectedOption = reservationChoiceBox.getValue();
			    if (selectedOption.equals("listeReservation")) {
			    	try {
						showListResvation();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//System.out.print(e1.getMessage());
					}
			    } else if (selectedOption.equals("listeReservationAccepté")) {
			    	try {
						showListResvationAccepté();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			});
			
			
			
			
			
			
			
			
		
			} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public void showLivre() throws Exception {
		LivreService ls = new LivreService();
		ObservableList<Livre> list =ls.getAllLivres();
		titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
		auteurLivre.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteur"));
		nbrExemplaireLivre.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("nbExemplaires"));
		codeRayon.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("codeRayon"));
		idLivre.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("idLivre"));
		filiere.setCellValueFactory(new PropertyValueFactory<Livre,String>("filiere"));
		dateAjouter.setCellValueFactory(new PropertyValueFactory<Livre,Date>("dateAjouter"));
		livreTabView.setItems(list);
	}
	
	public void ShowEmpruntList() throws SQLException {
		try {
			LivreService ls = new LivreService();
			ObservableList<EmpruntLivre> list =ls.getEmprunt();
			  ObservableList<EmpruntLivre> empruntFiltered = FXCollections.observableArrayList();

			for(EmpruntLivre empruntLivre: list) {
				if(empruntLivre.getDateRestitution() == null) {
					empruntFiltered.add(empruntLivre);
				}
			}
			emailEmprunt.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("email"));
			nomEmprunt.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("nom"));
			prenomEmprunt.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("prenom"));
			titreEmprunt.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("titre"));
			auteurEmprunt.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("auteur"));
			numeroEmp.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Integer>("idEmprunt"));
			
			empruntTableView.setItems(empruntFiltered);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void ajouterLivre() throws Exception{
			LivreService ls = new LivreService();
			Boolean exist = ls.checkIfTitleAndAuteurExist(titreTextField.getText(),auteurTextField.getText());
			if (exist == false) {
			Boolean bool=ls.addLivre(titreTextField.getText(),auteurTextField.getText(),nbrExemplaireTextField.getText(),codeRayonTextField.getText(),filiereTextField.getText());
			titreTextField.setText("");
			auteurTextField.setText("");
			nbrExemplaireTextField.setText("");
			codeRayonTextField.setText("");
			filiereTextField.setText("");
			showLivre();
				if (bool) {	
					 Alert alert = new Alert(AlertType.INFORMATION);
				        alert.setTitle("Livre ajouté");
				        alert.setHeaderText(null);
				        alert.setContentText("Le livre a été ajouté avec succès!");
				        alert.showAndWait();
					
				}else {
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText(null);
				alert.setContentText("Vous ne pouvez pas ajouter ce livre car il existe déjà.");

				alert.showAndWait();
			}	
	}
	
	public void modifierLivre() throws Exception{
		try {
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
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Information");
	        alert.setHeaderText("Livre est modifie");
	        alert.setContentText("le livre est modifié");
	        alert.showAndWait();
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void supprimerLivre() throws Exception{
		
		try {
			LivreService ls = new LivreService();
			TableViewSelectionModel<Livre> selectionModel = livreTabView.getSelectionModel();
			Livre livreSelectionne = selectionModel.getSelectedItem();
			int id = livreSelectionne.getIdLivre();
			boolean bool = ls.SupprimerLivre(id);
			showLivre();
			titreTextField.setText("");
			auteurTextField.setText("");
			nbrExemplaireTextField.setText("");
			codeRayonTextField.setText("");
			filiereTextField.setText("");
			if(bool == true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setHeaderText("Livre est Supprimer");
				alert.setContentText("le livre est Supprimer avec succes");
				alert.showAndWait();
				
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Errore");
				alert.setHeaderText("");
				alert.setContentText("vous pouvez pas supprimer le livre car il y a des abonnée qui ont emprunté le livre");
				alert.showAndWait();
				
			}
			
		} catch (Exception e) {
	
		}
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
	
	
	public void ajouterExemplaire() throws Exception {
		 TableViewSelectionModel<Livre> selectionModel = livreTabView.getSelectionModel();
		  Livre livreSelectionne = selectionModel.getSelectedItem();
		  LivreService ls = new LivreService();
		  int id = livreSelectionne.getIdLivre();
		  Boolean resultat = ls.ajouterExemplaire(id,ajouterExemplaireTextField.getText());
		  showLivre();
		  ajouterExemplaireTextField.setText("");
			titreTextField.setText("");
			auteurTextField.setText("");
			nbrExemplaireTextField.setText("");
			codeRayonTextField.setText("");
			filiereTextField.setText("");
		  if(resultat==true) {
			  Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Confirmation");
		        alert.setHeaderText("le nombre exemplaire est modifie");
		        alert.setContentText("");
		        alert.showAndWait();
			  
			 
		  }else {
			  
		  }
	}
	
	
	
	
	   
public void restituer() throws IOException, SQLException {
	try {
		LivreService ls = new LivreService();
		TableViewSelectionModel<EmpruntLivre> selectionModel = empruntTableView.getSelectionModel();
	    EmpruntLivre emprunt = selectionModel.getSelectedItem();

	    if (emprunt != null) {
	    	ls.updateRestitution(emprunt.getIdEmprunt());// changer  dateRestitustion =date.now()
	    	
	    	ls.updateExemplaireLivre(emprunt.getTitre(),emprunt.getAuteur());
	    	ShowEmpruntList();
	    	SendEmail sendEmail = new SendEmail();
	    	String subject = "Confirmation de restitution";
	    	String text = "Vous avez restituer le livre "+emprunt.getTitre();
	    	sendEmail.sendEmailMethode(emprunt.getEmail(),subject ,text);
	    	ls.decrementerNombreEmprintPourUtilisateur(emprunt.getEmail());
	    	Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Information");
	        alert.setHeaderText("Confirmation de restitution");
	        alert.setContentText("");
	        alert.showAndWait();
	    	
	    }
	} catch (Exception e) {
		// TODO: handle exception
	}
    }


// pour reservation tab 
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
	try {
		
		ObservableList<reservation> list =cs.getReservationList("0");
		completéTableau();
		reservationTable.setItems(list);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
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
	try {
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		
		if (selectedReservation != null) {
			
		   String email=selectedReservation.getEmail();
		   String titre =selectedReservation.getTitle();
		   String auteur=selectedReservation.getAuteur();
				
		   LocalDate dateReservation=selectedReservation.getDateReservation();
		   
		   int idLivre = cs.getIdLivre(titre,auteur);// car deux livre ne peuvent pas avoir le meme titre at auteur
		   int idUtilisateur=cs.getIdUtilisateur(email);
		   System.out.println(idLivre + " "+idUtilisateur);
		   
		   Boolean bool =cs.UpdateReservation(idUtilisateur,idLivre,dateReservation,"1");
		   System.out.println(dateReservation);
		  
		   cs.decrementerNombreExemplaire(idLivre);
		   //cs.incrementerNbrReservationUtilisateur(idUtilisateur);
		   //Pour email
		   SendEmail sendEmail = new SendEmail();
		   LocalDate date = LocalDate.now().plusWeeks(1);
		   showListResvation();
		   String subject = "Reservation Accepté";
		   String text ="la reservation de livre "+titre.toUpperCase()+"est accepté\n vous devez prendre votre livre avant"+date;
		   sendEmail.sendEmailMethode(email,subject,text);
		  
		    
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("Confirmation");
	       alert.setHeaderText("Reservation Accepté");
	       alert.setContentText("");
	       alert.showAndWait();
		   
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}
public void RefuserReservation() throws Exception {
	
	try {
		SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
		reservation selectedReservation = selectionModel.getSelectedItem();
		ConnectionService cs = new ConnectionService();
		
		if (selectedReservation != null) {
		
		   LocalDate dateReservation=selectedReservation.getDateReservation();
		   int idLivre = cs.getIdLivre(selectedReservation.getTitle(),selectedReservation.getAuteur());
		   int idUtilisateur=cs.getIdUtilisateur(selectedReservation.getEmail());

		   Boolean bool =cs.UpdateReservation(idUtilisateur,idLivre,dateReservation,"2");
		   cs.decrementerNbrReservationUtilisateur(idUtilisateur);
		   
		   SendEmail sendEmail = new SendEmail();
		   showListResvation();
		   LocalDate date = LocalDate.now().plusWeeks(1);
		   String subject = "Reservation Refusé";
		   String text ="la reservation de livre "+selectedReservation.getTitle().toUpperCase()+" est refusé";
		   sendEmail.sendEmailMethode(selectedReservation.getEmail(),subject,text);
		   Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("Confirmation");
	       alert.setHeaderText("Reservation refusé");
	       alert.setContentText("");
	       alert.showAndWait(); 
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
		
}
public void AjouterDansEmpurnt() throws Exception {
	
try {
	SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
	reservation selectedReservation = selectionModel.getSelectedItem();
	ConnectionService cs = new ConnectionService();
	if (selectedReservation != null) {
		int idLivre = cs.getIdLivre(selectedReservation.getTitle(),selectedReservation.getAuteur());
		int idUtilisateur=cs.getIdUtilisateur(selectedReservation.getEmail());
		LocalDate dateReservation=selectedReservation.getDateReservation();
		SendEmail sendEmail = new SendEmail();
		LocalDate date = LocalDate.now();   
		String subject = "Confirmation d'Emprunt";
		String text ="vous avez empruntez "+selectedReservation.getTitle().toUpperCase()+" vous devez le restituez avant "+date.plusMonths(1);
		
		cs.icrementerNombreEmprunt(idUtilisateur);
		cs.decrementerNbrReservationUtilisateur(idUtilisateur);
		
			cs.AjouterEmprunt(idUtilisateur,idLivre);
			cs.supprimerReservation(idUtilisateur,idLivre,dateReservation);
			
	
			
		showListResvationAccepté();
		ShowEmpruntList();
		sendEmail.sendEmailMethode(selectedReservation.getEmail(),subject,text);
		 Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("Information");
	       alert.setHeaderText("Reservation est ajouter a EmptuntList");
	       alert.setContentText("");
	       alert.showAndWait();
	
	}
	
} catch (Exception e) {
	
}
	
}
public void Supprimer() throws Exception {
try {
	SelectionModel<reservation> selectionModel = reservationTable.getSelectionModel();
	reservation selectedReservation = selectionModel.getSelectedItem();
	ConnectionService cs = new ConnectionService();
	
	if (selectedReservation != null) {
		
	   
	   LocalDate dateReservation=selectedReservation.getDateReservation();
	   int idLivre = cs.getIdLivre(selectedReservation.getTitle(),selectedReservation.getAuteur());
	   int idUtilisateur=cs.getIdUtilisateur(selectedReservation.getEmail());
	   
	   SendEmail sendEmail = new SendEmail();
	   
	   String subject = "Reservation Supprimer";
	   String text ="la reservation de livre "+selectedReservation.getTitle().toUpperCase()+" est Supprimer ";
	   sendEmail.sendEmailMethode(selectedReservation.getEmail(),subject,text);
	    
	   cs.supprimerReservation(idUtilisateur,idLivre,dateReservation);
	   cs.incrementerNombreExemplaire(idLivre);
	   cs.decrementerNbrReservationUtilisateur(idUtilisateur);
	   
}
	showListResvationAccepté();
	
	Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText("reservation est supprimer avec succes");
    alert.setContentText("");
    alert.showAndWait();
	
	
} catch (Exception e) {
	
}	
	
}


public void search() throws IOException, ClassNotFoundException, SQLException {
	
	LivreService ls = new LivreService();
	ObservableList<Livre> list =ls.getAllLivres();
	ObservableList<Livre> serachList =FXCollections.observableArrayList();
	for (Livre livre : list) {
		if(livre.getAuteur().toLowerCase().contains(searchTextField.getText().toLowerCase()) ||
				livre.getTitre().toLowerCase().contains(searchTextField.getText().toLowerCase()) ||
				livre.getFiliere().toLowerCase().contains(searchTextField.getText().toLowerCase())) {
			
			serachList.add(livre);
			
			
			
		}
	}
	livreTabView.setItems(serachList);
	
	
	
}


public void searchEmprunt() throws IOException, ClassNotFoundException, SQLException {
	
	LivreService ls = new LivreService();
	ObservableList<EmpruntLivre> list =ls.getEmprunt();
	ObservableList<EmpruntLivre> serachList =FXCollections.observableArrayList();
	for (EmpruntLivre emprunt : list) {
		if(emprunt.getAuteur().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase()) ||
				emprunt.getTitre().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase()) ||
				emprunt.getNom().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase())|| 
				emprunt.getPrenom().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase())|| 
				emprunt.getEmail().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase()) 
) {
			
			serachList.add(emprunt);
			
			
			
		}
	}
	empruntTableView.setItems(serachList);
	
	
	
}

public void searchReservation() throws Exception {
	ObservableList<reservation> list =FXCollections.observableArrayList();
	ConnectionService cs = new ConnectionService();
	String selectedOption = reservationChoiceBox.getValue();
    if (selectedOption.equals("listeReservation")) {
    	list = cs.getReservationList("0");
    }else {
    	 list = cs.getReservationList("1");
    	 for (reservation reservation : list) {
    			reservation.EstEnRetard();
    			System.out.println();
    		}
    	 
    	 
    	 
    	
    	
    	
    }
    
    
	ObservableList<reservation> serachList =FXCollections.observableArrayList();
	
	for (reservation reservation : list) {
		if(reservation.getAuteur().toLowerCase().contains(searchReservationTextField.getText().toLowerCase()) ||
				reservation.getTitle().toLowerCase().contains(searchReservationTextField.getText().toLowerCase()) ||
				reservation.getNom().toLowerCase().contains(searchReservationTextField.getText().toLowerCase())|| 
				reservation.getPrenom().toLowerCase().contains(searchReservationTextField.getText().toLowerCase())|| 
				reservation.getEmail().toLowerCase().contains(searchReservationTextField.getText().toLowerCase())|| 
				String.valueOf(reservation.getEstEnRetard()).toLowerCase().contains(searchReservationTextField.getText().toLowerCase()) 
				) {
			
			serachList.add(reservation);
			
			
			
		}
	}
	reservationTable.setItems(serachList);
	
	
	
}



	public void exit(ActionEvent e) {
    effect.exit(e);
}

public void cache(ActionEvent e) {
    effect.cache(e);
}
public void back(ActionEvent e) throws Exception {
    effect.switchStage(e,"Login.fxml");
}
}
    

