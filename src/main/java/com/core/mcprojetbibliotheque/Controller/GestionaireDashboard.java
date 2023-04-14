package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Utils.ListAdapter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionaireDashboard implements Initializable {
    private ListAdapter listAdapter;
    @FXML
    private AnchorPane acnhorePane;
    @FXML
    private VBox listItem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listAdapter=new ListAdapter(listItem,new ArrayList<Abonne>());
        listAdapter.build();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//        acnhorePane.getChildren().set(0,scene.getRoot());

    }

}
