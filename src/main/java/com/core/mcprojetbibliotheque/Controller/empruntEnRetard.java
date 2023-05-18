package com.core.mcprojetbibliotheque.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class empruntEnRetard implements Initializable {
	
		private WindowEffect effect;
	 	@FXML
	    private AnchorPane main;
	 	
	 	
	 	@FXML
	 	TextField searchEmpruntTextField;
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
				effect=new WindowEffect(main);
		    	
				ShowEmpruntEnRetard();
				
				
				searchEmpruntTextField.textProperty().addListener((ObservableList,oldValue,newValue)->{
					
					try {
						searchEmprunt();
					} catch (IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				});
				
				
				
				
				
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
				boolean resultat =cs.penaliserUtilisateur(selectedEmprunt.getEmail());
				LivreService ls = new LivreService();
				ls.addPenalisation(selectedEmprunt.getIdEmprunt());
				ShowEmpruntEnRetard();
				SendEmail sendEmail = new SendEmail();
				sendEmail.sendEmailMethode(selectedEmprunt.getEmail(),"Penalisation","vous etes penaliser jusque "+LocalDate.now().plusMonths(1)+"car vous n avez pas restituer le livre "+selectedEmprunt.getTitre());
			
			}
			
			
			
			
			
			
		}
		
		
		public void searchEmprunt() throws IOException, SQLException {
			LivreService ls = new LivreService();
			ObservableList<EmpruntLivre> list =ls.getEmprunt();
			ObservableList<EmpruntLivre> serachEmprunt =FXCollections.observableArrayList();
			for (EmpruntLivre emprunt : list) {
				if( 	emprunt.getTitre().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase()) ||
						emprunt.getNom().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase())|| 
						emprunt.getPrenom().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase())|| 
						emprunt.getEmail().toLowerCase().contains(searchEmpruntTextField.getText().toLowerCase()) 
		) {
					
					
					 emprunt.dernierDelais();
				     emprunt.EstEnRetard();
					if(emprunt.getDateRestitution() == null && emprunt.isEnRetard() == true && emprunt.isPenalisé()==false) {
						
						serachEmprunt.add(emprunt);
						
					}
					
				}
			}
			empruntEnRetardTableView.setItems(serachEmprunt);
			
			
			
			
			
			
			
			
			
			
			
			
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
