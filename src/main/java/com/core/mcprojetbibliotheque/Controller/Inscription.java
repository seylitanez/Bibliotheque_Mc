package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Service.ConnectionService;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.DialogGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.util.Date;

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
    private DialogGenerator dialogGenerator;
    private PopupInsControl popupInsController;
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        categorie.getItems().add("Enterne");
        categorie.getItems().add("Externe");
        categorie.getItems().add("Enseignant");


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

        connectionService.inscription(new Abonne(nom,prenom,username,password,email,categorie,new Date(System.currentTimeMillis()),certificatFile));


        dialogGenerator=new DialogGenerator("popupInscription.fxml");
        dialogGenerator.build();
        popupInsController=dialogGenerator.getFxmll().getController();
        dialogGenerator.setOnHidden(windowEvent -> {
            try {
                effect.switchStage(actionEvent,"login.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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
