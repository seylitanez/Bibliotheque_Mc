package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    private Stage stage;
    private double x,y;
    private ConnectionService connectionService;
    private WindowEffect effect;
    @FXML
    private AnchorPane main;
    @FXML
    private Button inscription;
    @FXML
    private TextField username,password;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        effect=new WindowEffect(stage,main);
        try{connectionService=new ConnectionService();
        }catch (Exception e){e.printStackTrace();}
    }

    public void connect() throws Exception {
        var username=this.username.getText();
        var password=this.password.getText();
        if(!username.isEmpty() && !password.isEmpty()) {
            connectionService.login(username,password);
            //reinitialisation
            this.username.setText("");
            this.password.setText("");
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
    public void inscrire(ActionEvent actionEvent) throws Exception {
        effect.switchStage(actionEvent,"inscription.fxml");
    }
}