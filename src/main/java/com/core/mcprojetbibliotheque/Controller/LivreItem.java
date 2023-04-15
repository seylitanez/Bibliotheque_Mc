package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Service.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LivreItem implements Initializable{
    @FXML
    private ImageView photo;
    @FXML
    private Label titre;
    private String cardTitre;
    @FXML
    private Button selectioner;
    private Livre livre;
    public MyListener myListener;
    public ImageView getPhoto() {
        return photo;
    }
    public Label getTitre() {
        return titre;
    }
    public Button getSelectioner() {
        return selectioner;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setData(Livre livre,MyListener myListener) throws FileNotFoundException {
        this.livre=livre;
        this.myListener=myListener;
        titre.setText(livre.getTitre());
        new Thread(()->{
        photo.setImage(new Image(livre.getPhoto()));
        }).start();
    }
    public void selectioner(ActionEvent actionEvent) throws IOException {
        myListener.OnClickList(livre);
        System.out.println();
    }
}