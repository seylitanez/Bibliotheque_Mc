package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupInsControl implements Initializable {

    private WindowEffect effect;

    @FXML
    private AnchorPane popup;

    public void close(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void dragged(MouseEvent e){
        effect.dragged(e);

    }
    public void pressed(MouseEvent e){
        effect.pressed(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        effect=new WindowEffect(popup);
    }
}
