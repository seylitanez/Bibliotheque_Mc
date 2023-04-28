package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Utils.DialogGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class lastInscriptionitemC {
    private Abonne utilisateur;
    @FXML
    private Label nom,prenom,email,categorie,statue;

    public void setItem(Abonne utilisateur){
        this.nom.setText(utilisateur.getNom());
        this.prenom.setText(utilisateur.getPrenom());
        this.email.setText(utilisateur.getEmail());
        this.categorie.setText(utilisateur.getCategorie());
        this.statue.setText("0");
        this.utilisateur=utilisateur;
    }
    public void clic (MouseEvent event) throws IOException {
        DialogGenerator dialogGenerator=new DialogGenerator("PopupConfirmation.fxml");
        dialogGenerator.show();
        PopupConfirmation popupConfirmation=dialogGenerator.getFxmll().getController();
        popupConfirmation.setUtilisateur(utilisateur);
        dialogGenerator.setOnHidden(windowEvent -> {
            if(popupConfirmation.isValide()){
                this.statue.setText("1");
            }
        });
    }
}
