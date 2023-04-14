package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Inscription implements Initializable {
    @FXML
    private TextField nom,prenom,username,email,password;
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private BorderPane borderPane;
    @FXML
    public ImageView certificat;
    private WindowEffect effect;
    @FXML
    private AnchorPane main;
    private ConnectionService connectionService;
    @FXML
    private Button sInscrire;
    private File certificatFile;
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        categorie.getItems().add("Etudiant");
        categorie.getItems().add("Etudiant externe");
        categorie.getItems().add("Enseignant");
        categorie.setValue("Etudiant");
        try {
            connectionService =new ConnectionService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        effect=new WindowEffect(main);
    }
    public void inscrire(ActionEvent actionEvent) throws Exception {
        sInscrire.setText("chargement");
        sInscrire.setDisable(true);
        var nom=this.nom.getText();
        var prenom=this.prenom.getText();
        var username=this.username.getText();
        var email=this.email.getText();
        var password=this.password.getText();
        var categorie=this.categorie.getSelectionModel().getSelectedItem();
        connectionService.inscription(nom,prenom,username,email,password,categorie,certificatFile,sInscrire);
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
        fileChooser.setTitle("Certificat de Scolarite");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.jpg","*.png","*.bmp"));
        certificatFile = fileChooser.showOpenDialog(Window.getWindows().get(0));
        System.out.println(certificatFile.getPath());
        certificat.setImage(new Image(new FileInputStream(certificatFile)));
    }
    public void onDragDropped(DragEvent dragEvent) throws FileNotFoundException {
        System.out.println("drag");
        certificatFile =dragEvent.getDragboard().getFiles().get(0);
        if (certificatFile.getPath().contains(".jpg") || certificatFile.getPath().contains(".png") || certificatFile.getPath().contains(".bmp")){
            System.out.println(certificatFile.getPath());
            certificat.setImage(new Image(new FileInputStream(certificatFile)));
        }
    }
    public void onDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }
}
