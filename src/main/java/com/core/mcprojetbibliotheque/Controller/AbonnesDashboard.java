package com.core.mcprojetbibliotheque.Controller;
import java.util.Calendar;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;

import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Model.EmpruntLivre;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Model.UtilisateurConnecté;
import com.core.mcprojetbibliotheque.Model.reservation;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.MyListener;
import com.core.mcprojetbibliotheque.Service.WindowEffect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
public class AbonnesDashboard implements Initializable {
    @FXML
    private AnchorPane main;
    
   
     
    @FXML
    private GridPane grid;

    @FXML
    private TextField rechercheLivre;
    @FXML
    private Button chercher;

    @FXML
    private Label nbExemplaires;
    
    private String Auteur;
    public Label getTitre() {
        return titre;
    }
    @FXML
    private Label titre;
    @FXML
    private ImageView photo;
    private List<Livre> livres;
    private MyListener listener;
    private WindowEffect effect;
    private ConnectionService connectionService;
    private DbConnexion dbConnexion;
    private LivreService livreService;
    @FXML
    private TableView<EmpruntLivre>abonneEmpruntTableView;
    @FXML
	public TableColumn<EmpruntLivre, String>title;
	@FXML
	public TableColumn<EmpruntLivre, String>auteur;
	@FXML
	public TableColumn<EmpruntLivre, LocalDate>dateRestitution;
	@FXML  
	public TableColumn<EmpruntLivre, Boolean>demandeProlongé;
	@FXML  
	public TableColumn<EmpruntLivre, Boolean>prolongé;
	@FXML
	public TableColumn<EmpruntLivre, Boolean>enRetard;
    
    
    @Override
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	effect=new WindowEffect(main);
    	
       



        try {
                livreService=new LivreService();
                livres= livreService.getAllLivres();
               
                
               

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//        livres.add(new Livre("titre 1","auteur1",5,"4216","https://www.oumma-design.fr/wp-content/uploads/2015/12/LE_DEVOIR_modifi%C3%A9-1.jpg"));
//        livres.add(new Livre("titre 2","auteur2",4,"4216","https://i.pinimg.com/originals/db/38/8d/db388d57cb3c1f9f4a8e3de7a7627510.jpg"));
//        livres.add(new Livre("titre 2","auteur2",4,"4216","https://www.babelio.com/users/blobid1608755607567.jpg"));
//        livres.add(new Livre("titre 2","auteur2",4,"4216","https://i1.wp.com/www.prom-auteur.com/wp-content/uploads/2018/01/couv-ebook-ok.jpg?w=1500"));
//        livres.add(new Livre("titre 2","auteur2",4,"4216","https://club-stephenking.fr/wp-content/uploads/2020/06/stephenking-marcheoucreve-lelivredepoche-couverture2020.jpg"));
//        livres.add(new Livre("titre 3","auteur1",2,"4216","target/classes/com/core/mcprojetbibliotheque/images/inscription.png"));
//        livres.add(new Livre("titre 4","auteur1",1,"4216","target/classes/com/core/mcprojetbibliotheque/images/plus.png"));
        listener=new MyListener() {
            @Override
            public void OnClickList(Livre livre) {
                System.out.println(livre.getTitre());
                System.out.println(livre.getTitre());
                selected(livre);
            }
        };

        afficherLivres();
    }
    public void afficherLivres(){
        int ligne=0;
        for (int colonne = 0; colonne < livres.size(); colonne++) {
            try {
                FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("LivreItem.fxml"));
                AnchorPane anchorPane= fxmlLoader.load();
                LivreItem livreItem=fxmlLoader.getController();
                livreItem.setData(livres.get(colonne), listener);
//                livreItem.getPhoto().setImage(new Image(livres.get(colonne).getPhoto()));
//            livreItem.selectioner(new ActionEvent());
                if (colonne%3==0) {
                    ligne++;
                }
                grid.add(anchorPane,(colonne%3)+1,ligne);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    
    
    
    
    private void selected(Livre livre){
        titre.setText(livre.getTitre());
        Auteur = livre.getAuteur();
       
        nbExemplaires.setText(livre.getNbExemplaires()+"");

        //asynchrone pour recuperer la photo
        new Thread(()->{
        photo.setImage(new Image(livre.getPhoto()));
        }).start();
        
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

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        var motCle=rechercheLivre.getText();
        System.out.println(livreService.searchLivres(motCle).get(0).getTitre());
        livres=livreService.searchLivres(motCle);
        grid.getChildren().clear();
        afficherLivres();

    //        System.out.println(livres.get(1).getTitre());}
}

    public void reserver(ActionEvent e)throws Exception {
    System.out.println(Auteur);
    	connectionService=new ConnectionService();
    	boolean payement = connectionService.checkPayement(UtilisateurConnecté.email);
    	if(payement == true) {
    		
    		boolean penalise = connectionService.checkPenalisation(UtilisateurConnecté.email);
        	if(penalise == false) {
        	
        	boolean possible = connectionService.checkPossiblity(UtilisateurConnecté.email);
        	
        	
        	
        	
        	if(possible==true ) {
        		ConnectionService cs = new ConnectionService();

    			int idLivre = cs.getIdLivre(titre.getText(),Auteur);
    			int idUtilisateur=cs.getIdUtilisateur(UtilisateurConnecté.email);
    			// vous pouvez faire la meme reservation pour meme livre dana la meme journé;
        		boolean reservationExiste= cs.checkIfReservationExiste(idUtilisateur,idLivre);
        		if(reservationExiste == false) {
        		

            		
           		 Dialog<ButtonType> dialog = new Dialog();
           		dialog.setTitle("CONFIRMATION");
           		dialog.setHeaderText("confirmer la reservation");
           		dialog.initModality(Modality.APPLICATION_MODAL);
           		Label label = new Label("voes etes sur vous voulez reservez le livre");
           		dialog.getDialogPane().setContent(label);
           		ButtonType okButton = new ButtonType("ok",ButtonBar.ButtonData.OK_DONE);
           		ButtonType cancalButton = new ButtonType("cancal",ButtonBar.ButtonData.CANCEL_CLOSE);
           		dialog.getDialogPane().getButtonTypes().addAll(okButton,cancalButton);
           		Optional<ButtonType> result = dialog.showAndWait();
           		if(result.isPresent()&& result.get()==okButton) {
           			
           		 
           			// add to database 
           		
           			cs.addReservarion(idLivre,idUtilisateur);
           			
           			// update nembre of reservation
           			cs.incrementerNbrReservationUtilisateur(idUtilisateur);
           			
           			
           		}	
        			
        			
        		}else {
        			Alert alert = new Alert(AlertType.ERROR);
        	        alert.setTitle("Erreur");
        	        alert.setHeaderText("Reservation impossible");
        	        alert.setContentText("vous pouvez pas reserver le meme livre dans le meme jour");
        	        alert.showAndWait();
        			
        			
        			
        		}
        		
        		
        		
        		
        		
        		
        		  
        		
        	}else {
        		
        		Alert alert = new Alert(AlertType.ERROR);
    	        alert.setTitle("Erreur");
    	        alert.setHeaderText("code invalide");
    	        alert.setContentText("vous avez reserver ou emprunter plus de trois");
    	        alert.showAndWait();
        		
        		
        	}
       	
        	}else {
        		
        		Alert alert = new Alert(AlertType.ERROR);
    	        alert.setTitle("Erreur");
    	        alert.setHeaderText("vous etes penalisé");
    	        alert.setContentText("vous pouvez pas reserver car vous etes penalisé");
    	        alert.showAndWait();
        		
        		
        	}	
    		
    	
    	
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("payement");
	        alert.setContentText("il faut payer pour reserver et emprunter !");
	        alert.showAndWait();
    		
    		
    	}
    }
 
    
    
    public void VoirListEmprunt(ActionEvent a) throws Exception {
    	
    	try {
    		effect.switchStage(a,"AbonneListEmprunt.fxml");
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
    	
    	
    }
   
public void showAbooneEmprunt() throws IOException, SQLException {
	   LivreService ls = new LivreService();
		ObservableList<EmpruntLivre> list =ls.getEmprunt();
		for (EmpruntLivre emprunt : list) {
			emprunt.EstEnRetard();
		}
		ObservableList<EmpruntLivre> empruntFiltered = FXCollections.observableArrayList();
		for (EmpruntLivre emprunt : list) {
			if(emprunt.getEmail().equals("lyes")) {
				empruntFiltered.add(emprunt);	
			}
		}
	   title.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("title"));
	   auteur.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,String>("auteur"));
	   dateRestitution.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,LocalDate>("dateRestitution"));
	   demandeProlongé.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("demandeProlonger"));
	   prolongé.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("prolonge"));
	   enRetard.setCellValueFactory(new PropertyValueFactory<EmpruntLivre,Boolean>("enRetard"));
	   abonneEmpruntTableView.setItems(empruntFiltered);

   }
    
    
}