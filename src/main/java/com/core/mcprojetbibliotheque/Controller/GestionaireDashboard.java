package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.ListAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;

public class GestionaireDashboard implements Initializable {
    private ListAdapter listAdapter;
    private WindowEffect effect;
    @FXML
    private AnchorPane anchorPane,main;
    @FXML
    private VBox listItem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        effect=new WindowEffect(main);
        listAdapter=new ListAdapter(listItem,new ArrayList<Abonne>());
        listAdapter.build();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//        acnhorePane.getChildren().set(0,scene.getRoot());
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
}