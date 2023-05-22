package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
	 private AnchorPane main;
	 
	 private WindowEffect effect;
	 
	 @FXML
	 TextField searchDemandeTextField;
	
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
			effect=new WindowEffect(main);
			showDemande();
			searchDemandeTextField.setPromptText("search : email, nom, prenom, title ");
			
			searchDemandeTextField.textProperty().addListener((ObservableList,oldValue,newValue)->{
				
				
				try {
					searchDemande();
				} catch (IOException | SQLException e) {
					
				System.out.print(e.getMessage());
				}
				
				
				
				
			});
			
			
			
			
			
			
			
			
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void showDemande() throws  SQLException, IOException {
		LivreService ls = new LivreService();
		   ObservableList<EmpruntLivre> list = ls.getEmprunt();
		 
		  for(EmpruntLivre emprunt :list) {
			  emprunt.dernierDelais();
		  }
		   // filtrer les emprunt 
		   ObservableList<EmpruntLivre> empruntsFiltres = list
				    .filtered(e -> (e.isDemandeProlonger() && e.getDateRestitution() == null && e.isEnRetard() == false && e.isProlongéRufusé()==false && e.isProlonge()==false));
				    
		   
		   
		   
		   
		   
		   email.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("email"));
		   nom.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("nom"));
		   prenom.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("prenom"));
		   titre.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("titre"));
		   nbrExemplaire.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Integer>("nbrExemplaire"));
		   Delais.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,LocalDate>("Delais"));
		   demandeProlongerTableView.setItems(empruntsFiltres);
	
	}



	public void accepter() throws IOException, SQLException {
		try {
			SelectionModel<EmpruntLivre> selectionModel = demandeProlongerTableView.getSelectionModel();
			EmpruntLivre selectedEmprunt = selectionModel.getSelectedItem();
			if(selectedEmprunt != null) {
				
				LivreService ls =new  LivreService();
				ls.accepterDemande(selectedEmprunt.getIdEmprunt());
				
				showDemande();
				SendEmail sendEmail = new SendEmail();
				sendEmail.sendEmailMethode(selectedEmprunt.getEmail(),"Demande de Prolongé est accepté", "le dernier delais pour restituer livre "+selectedEmprunt.getTitre()+" sera "+selectedEmprunt.getDelais());
				 Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Information");
			        alert.setHeaderText("Demande Accepté");
			        alert.setContentText("Demande est accepté");
			        alert.showAndWait();
			
			
			}
		}catch (Exception e) {
			
		}
		
	
		
		
		
		
		
		}
		
		
		 
		
		
	
	public void refuser() throws IOException, SQLException {
		try {
			SelectionModel<EmpruntLivre> selectionModel = demandeProlongerTableView.getSelectionModel();
			EmpruntLivre selectedEmprunt = selectionModel.getSelectedItem();
			if(selectedEmprunt != null) {
				
				LivreService ls =new  LivreService();
				ls.ReffuserDemande(selectedEmprunt.getIdEmprunt());
				showDemande();
				SendEmail sendEmail = new SendEmail();
				sendEmail.sendEmailMethode(selectedEmprunt.getEmail()," Demande de Prolongé est reffusé","Votre deamande de prolonge le delais de restitution le livre "+selectedEmprunt.getTitre()+" est reffusé \n vous devez restituer le livre avant "+selectedEmprunt.getDelais() );
				 Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Demande Rufusé");
			        alert.setHeaderText(null);
			        alert.setContentText("demande est refusé");
			        alert.showAndWait();
			
			}
		} catch (Exception e) {
			
		}
	}
	
	
	
	
	public void searchDemande() throws IOException, SQLException {
		
		
		
		LivreService ls = new LivreService();
		ObservableList<EmpruntLivre> list =ls.getEmprunt();
		ObservableList<EmpruntLivre> serachDemande =FXCollections.observableArrayList();
		for (EmpruntLivre emprunt : list) {
			if( 	emprunt.getTitre().toLowerCase().contains(searchDemandeTextField.getText().toLowerCase()) ||
					emprunt.getNom().toLowerCase().contains(searchDemandeTextField.getText().toLowerCase())|| 
					emprunt.getPrenom().toLowerCase().contains(searchDemandeTextField.getText().toLowerCase())|| 
					emprunt.getEmail().toLowerCase().contains(searchDemandeTextField.getText().toLowerCase()) 
	) {
				 emprunt.dernierDelais();
				serachDemande.add(emprunt);
				
				
				
			}
		}
		demandeProlongerTableView.setItems(serachDemande);
		
		
		
		
		
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
