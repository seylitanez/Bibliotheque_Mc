package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.*;
import javafx.scene.control.Button;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.*;
import java.sql.*;
import java.util.*;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public Object login(String us, String pass) {
        try (var cnx =dbConnexion.getConnection()) {
            PreparedStatement statement = cnx.prepareStatement(TROUVE_UTILISATEUR);
            statement.setString(1, us);
            statement.setString(2, pass);
            // resultSet me renvoi le resultat de la requete select
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            var nom = "";
            var prenom  = "";
            var username = "";
            var password = "";
            var email = "";
            var categorie = "";
            var certificat = "";
            while (resultSet.next()) {
                nom = resultSet.getString("nom");
                prenom = resultSet.getString("prenom");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                categorie = resultSet.getString("categorie");
                certificat = resultSet.getString("certificat");
            }
//            if (categorie == "Etudiant"){
//                return new Etudiant();
//            } else if (categorie == "Etudiant externe") {
//                return new Etudiant( nom, prenom,username,password, email,categorie,certificat);
//
//                new Etudiant( nom,  prenom,  email,  password,  categorie,  new Date(System.currentTimeMillis()),  true,  certificat);
//
//            } else if (categorie == "Enseignant") {
//                return new Enseignant( nom, prenom,username,password, email,categorie,certificat);
//            }else if (categorie == "Bibliothecaire") {
//                return new Bibliothecaire( nom, prenom,username,password, email);
//            }else if (categorie == "Gestionnaire") {
//                return new Gestionnaire( nom, prenom,username,password, email,categorie);
//            }
//            switch (categorie){
//                case "Etudiant"->{
//                    return new Etudiant(nom,prenom,email,password,categorie,certificat,new Date(System.currentTimeMillis()),3,false,true);
//                }
//                case "Etudiant externe"->{
//                    return new Etudiant(nom,prenom,email,password,categorie,certificat,new Date(System.currentTimeMillis()),3,false,true);
//                }
//                case "Enseignant"->{
//                    return new Enseignant(nom,prenom,email,password,categorie,certificat,new Date(System.currentTimeMillis()),3,false,true);
//                }
//
//            }
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
    public void inscription(Abonne abonne) throws Exception {

        var nom= abonne.getNom();
        var prenom= abonne.getPrenom();
        var username= abonne.getUsername();
        var email= abonne.getEmail();
        var password= abonne.getPassword();
        var categorie= abonne.getCategorie();
        var certificatFile= abonne.getCertificat();

        System.out.println("chargement...");
        JDABuilder jdab= JDABuilder.createDefault("MTA5NjE3NzczMzkzNzEzNTc0Ng.Gzj8qY.YIiHfiv0Tta1E5zoMlEtakrTt1gkF2BMAEDiek");
        jdab.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        jdab.addEventListeners(new ListenerAdapter() {
            @Override
            public void onReady(ReadyEvent event) {
            try {
                System.out.println(certificatFile.getPath());
                File file = new File(certificatFile.getPath());
                event.getJDA().getTextChannelById("1096178322393804871").sendFiles(FileUpload.fromData(file)).queue(message -> {
                    String pathOut=message.getAttachments().get(0).getUrl();
                    PreparedStatement statement= null;
                    try {
                        System.out.println("execution de la requete sql");
                        statement = dbConnexion.getConnection().prepareStatement(AJOUT_UTILISATEUR);
                        statement.setString(1,nom);
                        statement.setString(2,prenom);
                        statement.setString(3,username);
                        statement.setString(4,password);
                        statement.setString(5,email);
                        statement.setString(6,categorie);
                        statement.setString(7, pathOut);
                        statement.execute();
                        System.out.println("inscription finit");
                        System.out.println("apres btn");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
//                    sInscrire.setText("s'inscrire");
                System.out.println("file sent");
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        });
        jdab.setStatus(OnlineStatus.ONLINE);
        JDA jda=jdab.build();
        jda.updateCommands();
        System.out.println("*******************/////////////////");
    }
}