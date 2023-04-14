package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Utils.ListAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class GestionaireDashboard implements Initializable {
    private ListAdapter listAdapter;
    @FXML
    private AnchorPane acnhorePane;

    @FXML
    private TableView dernieresInscriptions;

    @FXML
    private TableView ins;
    @FXML
    private VBox listItem;

    @FXML
    private TableColumn<Abonne,String> nom;
    @FXML
    private TableColumn<Abonne,String> prenom;
    @FXML
    private TableColumn<Abonne,String> email;
    @FXML
    private TableColumn<Abonne,String> categorie;

//    @FXML
//    private TableColumn<Abonne,String> status;
    ArrayList<Abonne> abonnes =new ArrayList<>();


    private ObservableList<Abonne> getAbonnes(){

        abonnes.add(new Abonne("nom","prenom","email","pass","categ", new Date(System.currentTimeMillis())));
        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));
        abonnes.add(new Abonne("test","test","email","test","test", new Date(System.currentTimeMillis())));

        ObservableList observableList= FXCollections.observableArrayList(abonnes);

        return  observableList;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Abonne,String>("email"));
        categorie.setCellValueFactory(new PropertyValueFactory<Abonne,String>("categorie"));
//        status.setCellValueFactory(new PropertyValueFactory<Abonne,String>("status"));
//        nom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("nom"));




        dernieresInscriptions.setItems(getAbonnes());
        dernieresInscriptions.getItems().stream().forEach(System.out::println);
//        dernieresInscriptions.

        listAdapter=new ListAdapter(listItem,new ArrayList<Abonne>());
        listAdapter.build();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//        acnhorePane.getChildren().set(0,scene.getRoot());

    }

}
