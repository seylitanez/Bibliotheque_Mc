package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import javafx.scene.control.Button;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.*;
import java.sql.*;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public void login(String username, String password) {
        try (var cnx =dbConnexion.getConnection()){
            PreparedStatement statement = cnx.prepareStatement(TROUVE_UTILISATEUR);
            statement.setString(1, username);
            statement.setString(2, password);
            // resultSet me renvoi le resultat de la requete select
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                var  us = resultSet.getString("username");
                var  pss = resultSet.getString("password");
                System.out.println(us+' '+pss);
            }
            statement.execute();
        }catch (Exception e){e.getMessage();}
    }
    public void inscription(String nom, String prenom, String username, String email, String password, String categorie, File certificatFile, Button sInscrire) throws Exception {
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
