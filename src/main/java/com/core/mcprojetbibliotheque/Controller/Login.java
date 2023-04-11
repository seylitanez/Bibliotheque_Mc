package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.core.mcprojetbibliotheque.Utils.Constantes.*;
public class Login {


    private Stage stage;
    private DbConnexion dbConnexion=new DbConnexion();
    @FXML
    private Button inscription;
    @FXML
    private TextField username,password;

    public Login() throws IOException {
    }


    public void exit(ActionEvent e) {
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();


    }

    public void inscrire(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        var username=this.username.getText();
        var password=this.password.getText();

        System.out.println(username);
        if(!username.isEmpty() && !password.isEmpty()) {
            var cnx = dbConnexion.getConnection();

            PreparedStatement statement = cnx.prepareStatement(AJOUT_UTILISATEUR);

            statement.setString(1, username);
            statement.setString(2, password);
            System.out.println("utilisateur ajoute avec succes");
            inscription.setText("s'inscrire");

            //reinitialisation
            this.username.setText("");
            this.password.setText("");

        }


    }
}