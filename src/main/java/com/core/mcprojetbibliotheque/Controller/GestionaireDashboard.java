package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Configuration.PrepareStatementP;
import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.ListAdapter;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;
import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

public class GestionaireDashboard implements Initializable {
    private ListAdapter listAdapter;
    @FXML
    private AnchorPane main;
    private WindowEffect effect;
    private PrepareStatementP statementP;
//    @FXML
//    private TableView<Abonne> dernieresInscriptions;
    @FXML
    private TableView ins;
    @FXML
    private VBox listItem;
//    @FXML
//    private TableColumn<Abonne,String> nom;
//    @FXML
//    private TableColumn<Abonne,String> prenom;
//    @FXML
//    private TableColumn<Abonne,String> email;
//    @FXML
//    private TableColumn<Abonne,String> categorie;
    //    @FXML
//    private TableColumn<Abonne,String> status;
    ArrayList<Abonne> abonnes =new ArrayList<>();
    private ObservableList<Abonne> getAbonnes(){
//        abonnes.add(new Abonne("nom","prenom","email","pass","categ", new Date(System.currentTimeMillis())));
//        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
//        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
//        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
//        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
        ObservableList observableList= FXCollections.observableArrayList(abonnes);
        return  observableList;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        nom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("nom"));
//        prenom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("prenom"));
//        email.setCellValueFactory(new PropertyValueFactory<Abonne,String>("email"));
//        categorie.setCellValueFactory(new PropertyValueFactory<Abonne,String>("categorie"));
//        status.setCellValueFactory(new PropertyValueFactory<Abonne,String>("status"));
//        nom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("nom"));
        effect=new WindowEffect(main);
//        dernieresInscriptions.setItems(getAbonnes());
//        dernieresInscriptions.getItems().stream().forEach(System.out::println);
//        dernieresInscriptions.
        try {
            statementP =new PrepareStatementP(TROUVE_UTILISATEUR_NOAUTH);
            listAdapter=new ListAdapter(listItem,statementP.data());
            listAdapter.build();
        } catch (Exception e) {
            e.printStackTrace();
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
        effect.switchStage(e,"Login.fxml");
    }
    
    
    
    
    
    
    
    
    
    public void showUtilisateur(ActionEvent e) throws Exception {
    	 effect.switchStage(e,"GestioneDesUtilisateur.fxml");
    
    }
    public void demandeProlonger(ActionEvent e) throws Exception{
    	try {
			
    		effect.switchStage(e,"demandeProlongé.fxml");
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
    
    }
    public void empruntRetard(ActionEvent e3) throws Exception{
    	try {
			
    		effect.switchStage(e3,"empruntEnRetard.fxml");
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
    }
    
    
}