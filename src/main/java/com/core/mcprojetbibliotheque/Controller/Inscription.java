package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Inscription implements Initializable {
    @FXML
    public ImageView certificat;
    private WindowEffect effect;
    @FXML
    private AnchorPane main;


    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        effect=new WindowEffect(main);
    }

    public void inscrire(ActionEvent actionEvent) {
        System.out.println("inscrire");
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
    public void back(ActionEvent e) throws Exception {
        effect.switchStage(e,"login.fxml");
    }
    public void certificat(MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser=new FileChooser();
        var file= fileChooser.showOpenDialog(Window.getWindows().get(0));
        fileChooser.setTitle("Certificat de Scolarite");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Images","*.png","*.png","*.bmp"));
        certificat.setImage(new Image(new FileInputStream(file)));
    }
}
