package com.core.mcprojetbibliotheque.Controller;
import java.util.Calendar;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;

import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Model.UtilisateurConnecté;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.LivreService;
import com.core.mcprojetbibliotheque.Service.MyListener;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    	connectionService=new ConnectionService();
    	
    	// parce que interdit de emprunté plus de trois ouvreage
    	boolean possible = connectionService.checkPossiblity(UtilisateurConnecté.email);
    	
    	
    	
    	
    	if(possible==true ) {
    		
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
    			
    			
    			//1:on a titre de livre ;
    			// :il faut recuperer id de livre 
    			// mais il faut mettre  title unique 
    			
    			ConnectionService cs = new ConnectionService();
    			int idLivre = cs.getIdLivre(titre.getText());
    			int idUtilisateur=cs.getIdUtilisateur(UtilisateurConnecté.email);
    			LocalDate date = LocalDate.now(); // ou LocalDate.of(2021, 3, 24) pour une date spécifique
    			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    			String formattedDate = date.format(formatter);
    			
    			cs.addReservarion(idLivre,idUtilisateur,formattedDate);
    			
    			// update nembre of reservation
    			cs.updateNbrReservation(idUtilisateur);
    			
    			
    		}
    		
    		
    		
    	}else {
    		
    		Dialog<String> dialog = new Dialog<>();
    		dialog.setTitle("Erreur");
    		dialog.setHeaderText("Vous pouvez pas reservez maintent");
    		dialog.setContentText("vous avez reservez ou empuntez plus de trois.");
    		
    		ButtonType okButton = new ButtonType("OK");
    		dialog.getDialogPane().getButtonTypes().add(okButton);
    		
    		dialog.showAndWait();
    		
    		
    	}
   	
    }
 
   
    
    
}