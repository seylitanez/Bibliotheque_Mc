package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.PreparedStatement;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;


public class PopupConfirmation {
    private Utilisateur utilisateur;
    private boolean valide=false;
    @FXML
    private ImageView pdf;

    public boolean isValide() {
        return valide;
    }

    public void acsept(ActionEvent event) throws Exception {
        PreparedStatement preparedStatement=new DbConnexion().getConnection().prepareStatement(MODIFIER_UTILISATEUR);
        preparedStatement.setInt(1,utilisateur.getId());
        preparedStatement.execute();
        valide=true;
        exit(event);
    }

    public void refuser(ActionEvent event){
        exit(event);
    }
    private void exit(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setUtilisateur(Abonne utilisateur) {
        this.utilisateur = utilisateur;
        pdf.setImage(new Image(utilisateur.getCertificat().getPath().replace('\\','/').replaceFirst("/","//")));
        //TODO:affichage du nom prenom etc...
    }
}
