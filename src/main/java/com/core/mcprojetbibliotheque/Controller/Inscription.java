package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.DialogGenerator;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class Inscription implements Initializable {
    @FXML
    private TextField nom,prenom,username,email,password;
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private BorderPane borderPane;
    @FXML
    public ImageView certificat;
    private WindowEffect effect;
    @FXML
    private AnchorPane main;
    private ConnectionService connectionService;
    @FXML
    private Button sInscrire;
    private File certificatFile;
    private DialogGenerator dialogGenerator;
    private PopupInsControl popupInsController;
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        categorie.getItems().add("Enterne");
        categorie.getItems().add("Externe");
        categorie.getItems().add("Enseignant");
        try {
            connectionService =new ConnectionService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        effect=new WindowEffect(main);
    }
    public void inscrire(ActionEvent actionEvent) throws Exception {
    	if(nom.getText()=="" || prenom.getText() == "" || email.getText()== "" || password.getText() == "" || username.getText() == "") {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erreur");
	        alert.setHeaderText("information invalide");
	        alert.setContentText("completez les information");
	        alert.showAndWait();
    	}else {
    	ConnectionService cs = new ConnectionService();
    	boolean exist = cs.checkEmailIfExist(email.getText());
    	if(exist == true) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erreur");
	        alert.setHeaderText("Email Exist Deja");
	        alert.setContentText("Cet Email Exist Deja ! ");
	        alert.showAndWait();
    	}else {
    		
    	
    	//confirmation d email 
    	
    	
    	 Random rand = new Random();
         int randomNum = rand.nextInt(900000) + 100000;
    	
         
         try {
        	 SendEmail sendEmail = new SendEmail();
         	//sendEmail.sendEmailMethode(email.getText(),"Confirmation de votre email", "le code de confirmation est "+randomNum);
         	
 		} catch (Exception e2) {
 			System.out.println(e2.getMessage());
 		}
         
         
         
    	
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Confirmation email");
    	dialog.setHeaderText("Entrez le code de verification qui a été envoyé a votre email:");
    	dialog.setContentText("code:");
    	Optional<String> result = dialog.showAndWait();
    	
    	if (result.isPresent()) {
    	    try {
    	       // int code = Integer.parseInt(result.get());
    	    	int code =1;
    	        if(code == 1) {
    	        	
    	        	
    	        	
    	            sInscrire.setText("chargement");
    	            sInscrire.setDisable(true);
    	            var nom=this.nom.getText();
    	            var prenom=this.prenom.getText();
    	            var username=this.username.getText();
    	            var email=this.email.getText();
    	            var password=this.password.getText();
    	            var categorie=this.categorie.getSelectionModel().getSelectedItem();
    	            connectionService.inscription(new Abonne(nom,prenom,username,password,email,categorie,new Date(System.currentTimeMillis()),certificatFile));
    	            dialogGenerator=new DialogGenerator("popupInscription.fxml");
    	            dialogGenerator.build();
    	            popupInsController=dialogGenerator.getFxmll().getController();
    	            dialogGenerator.setOnHidden(windowEvent -> {
    	                try {
    	                    effect.switchStage(actionEvent,"login.fxml");
    	                } catch (Exception e) {
    	                    throw new RuntimeException(e);
    	                }
    	            });
    	        	
    	        	
    	        	
    	        	
    	        	
    	        	
    	        	
    	        }else {
    	        	Alert alert = new Alert(AlertType.ERROR);
        	        alert.setTitle("Erreur");
        	        alert.setHeaderText("code invalide");
        	        alert.setContentText("La code saisie n'est pas un valide.\nveillez ressayer l'insecription");
        	        alert.showAndWait();
    	        }
    	        
    	    } catch (NumberFormatException e) {
    	       
    	        Alert alert = new Alert(AlertType.ERROR);
    	        alert.setTitle("Erreur");
    	        alert.setHeaderText("Valeur invalide");
    	        alert.setContentText("La valeur saisie n'est pas un entier.");
    	        alert.showAndWait();
    	    }
    	    
    	}   
    	}
    	    
    	    
    	    
    	}
    	
    	
    	
  
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
    public void certificat(MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Certificat de Scolarite");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.jpg","*.png","*.bmp"));
        certificatFile = fileChooser.showOpenDialog(Window.getWindows().get(0));
        System.out.println(certificatFile.getPath());
        certificat.setImage(new Image(new FileInputStream(certificatFile)));
    }
    public void onDragDropped(DragEvent dragEvent) throws FileNotFoundException {
        System.out.println("drag");
        certificatFile =dragEvent.getDragboard().getFiles().get(0);
        if (certificatFile.getPath().contains(".jpg") || certificatFile.getPath().contains(".png") || certificatFile.getPath().contains(".bmp")){
            System.out.println(certificatFile.getPath());
            certificat.setImage(new Image(new FileInputStream(certificatFile)));
        }
    }
    public void onDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }
}