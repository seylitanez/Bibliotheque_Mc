package com.core.mcprojetbibliotheque.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Inscription implements Initializable {
    @FXML
    public ImageView certificat;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {

    }

    public void inscrire(ActionEvent actionEvent) {
        System.out.println("inscrire");
    }

    public void certificat(MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser=new FileChooser();
        var file= fileChooser.showOpenDialog(Window.getWindows().get(0));

        fileChooser.setTitle("Certificat de Scolarite");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Images","*.png","*.png","*.bmp"));
        certificat.setImage(new Image(new FileInputStream(file)));

    }
}
