package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.core.mcprojetbibliotheque.Utils.Constantes.*;
public class Login {


    private Stage stage;
    private DbConnexion dbConnexion=new DbConnexion();
    @FXML
    private Button inscription;
    @FXML
    private TextField username,password;
    @FXML
    private ImageView image;

    public Login() throws IOException {

    }

    public void connect() throws SQLException, ClassNotFoundException, FileNotFoundException {
        System.out.println("login");
////        image.setImage(new Image());
//        var cnx= dbConnexion.getConnection();
//        var preparedStatement=cnx.prepareStatement("select * from utilisateur;");
//        ResultSet resultSet= preparedStatement.executeQuery();
//
//        while(resultSet.next()) {
//        var username= resultSet.getString("username");
//
//        if (resultSet.isLast()){
//            var photoStream=resultSet.getBlob("photo").getBinaryStream();
//             image.setImage(new Image(photoStream));
//            }
//        }

        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Certificat de scolarite");

        var file=fileChooser.showOpenDialog(Window.getWindows().get(0));

             image.setImage(new Image(new FileInputStream(file)));


        System.out.println(file.getPath());

    }


    public void exit(ActionEvent e) {
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();


    }

    public void inscrire(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, FileNotFoundException {

        var username=this.username.getText();
        var password=this.password.getText();

        if(!username.isEmpty() && !password.isEmpty()) {
            try (var cnx =dbConnexion.getConnection()){


            PreparedStatement statement = cnx.prepareStatement(AJOUT_UTILISATEUR);

            statement.setString(1, username);
            statement.setString(2, password);

            statement.setBlob(3,new FileInputStream("C:\\Users\\lyes\\Desktop\\one_piece.jpg"));

            statement.execute();
            //reinitialisation
            this.username.setText("");
            this.password.setText("");
            System.out.println("utilisateur ajoute avec succes");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}