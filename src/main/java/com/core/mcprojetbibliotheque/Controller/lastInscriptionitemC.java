package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class lastInscriptionitemC {
    private Utilisateur utilisateur;
    @FXML
    private Label nom,prenom,email,categorie,statue;

    public void setItem(Utilisateur utilisateur){
        this.nom.setText(utilisateur.getNom());
        this.prenom.setText(utilisateur.getPrenom());
        this.email.setText(utilisateur.getEmail());
        this.categorie.setText(utilisateur.getCategorie());
        this.statue.setText("0");
        this.utilisateur=utilisateur;
    }
    public void clic (MouseEvent event){

    }
}
