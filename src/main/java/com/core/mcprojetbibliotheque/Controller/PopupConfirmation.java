package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Model.Utilisateur;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import com.core.mcprojetbibliotheque.Utils.SendEmail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;


public class PopupConfirmation implements Initializable {
    private Utilisateur utilisateur;
    private WindowEffect effect;
    private boolean valide=false;
    @FXML
    private AnchorPane main;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label  prenom;
    @FXML
    private Label  categorie;
    
    @FXML
    private ImageView pdf;

    public boolean isValide() {
        return valide;
    }

    SendEmail sendEmail = new SendEmail();
    
    public void acsept(ActionEvent event) throws Exception {
        PreparedStatement preparedStatement=new DbConnexion().getConnection().prepareStatement(MODIFIER_UTILISATEUR);
        preparedStatement.setInt(1,utilisateur.getId());
        preparedStatement.execute();
        valide=true;
        sendEmail.sendEmailMethode(utilisateur.getEmail(), "Insecription Accepté ", "vous etes accepté \nVous pouvez connectez maintent");
        exit(event);
    }

    public void refuser(ActionEvent event){
        exit(event);
        sendEmail.sendEmailMethode(utilisateur.getEmail(), "Insecription refusé ", "vous etes refusé \n veuillez s'inscrire avec information correct ");
    }
    public void exit(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setUtilisateur(Abonne utilisateur) {
        this.utilisateur = utilisateur;
        pdf.setImage(new Image(utilisateur.getCertificat().getPath().replace('\\','/').replaceFirst("/","//")));
       
        nom.setText(utilisateur.getNom());
        prenom.setText(utilisateur.getPrenom());
        email.setText(utilisateur.getEmail()); 
        categorie.setText(utilisateur.getCategorie());
        
    }
    
    public void cache(ActionEvent e) {
        effect.cache(e);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		effect=new WindowEffect(main);
	}
    
    
    
    
    
    
    
    
    
}
