package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    private ConnectionService connectionService;
    private WindowEffect effect;
    @FXML
    private AnchorPane main;
    @FXML
    private TextField username,password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        effect=new WindowEffect(main);
    }
    public void connect(ActionEvent e) throws Exception {
        connectionService=new ConnectionService();
        var username=this.username.getText();
        var password=this.password.getText();
        if(!username.isEmpty() && !password.isEmpty()) {
            System.out.println(username);
            System.out.println(password);
            Utilisateur utilisateur= connectionService.login(username,password);
            this.username.setText("");
            this.password.setText("");
            Thread thread= new Thread(()->{
                try {
                    switch (utilisateur.getCategorie()){
                        case "gestionaire","bibliothecaire":effect.switchStage(e,"GestionaireDashboard.fxml");break;
                        case "Externe","Interne","Enseignant":effect.switchStage(e,"AbonneDashboard.fxml");break;
                    }
                }catch (Exception exception){}
            });
            thread.join();
            thread.start();
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
