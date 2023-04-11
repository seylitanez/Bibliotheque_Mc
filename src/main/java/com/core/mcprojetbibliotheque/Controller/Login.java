package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Service.ConnectionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;
public class Login implements Initializable {
    private Stage stage;
    @FXML
    private Button inscription;
    @FXML
    private TextField username,password;
    private ConnectionService connectionService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ConnectionService connectionService=new ConnectionService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Login() throws Exception {
    }
    public void exit(ActionEvent e) {
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
    public void inscrire(ActionEvent actionEvent) throws Exception {
        var username=this.username.getText();
        var password=this.password.getText();
        System.out.println(username);
        if(!username.isEmpty() && !password.isEmpty()) {
            connectionService.login(username,password);
            inscription.setText("s'inscrire");
            //reinitialisation
            this.username.setText("");
            this.password.setText("");
        }
    }
}