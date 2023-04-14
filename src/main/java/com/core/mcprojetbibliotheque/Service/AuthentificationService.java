package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Utils.Commandes;
import com.core.mcprojetbibliotheque.Utils.Constantes;
import javafx.scene.control.Button;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.core.mcprojetbibliotheque.Utils.Constantes.AJOUT_UTILISATEUR;

public class AuthentificationService {

    public void inscription(String nom, String prenom, String username, String email, String password, String categorie, File certificatFile, Connection connection, Button sInscrire) throws InterruptedException, SQLException {
//       Thread pictureRegesterTh=new Thread(new Runnable() {
//           @Override
//           public void run() {
               System.out.println("chargement...");
               JDABuilder jdab= JDABuilder.createDefault("MTA5NjE3NzczMzkzNzEzNTc0Ng.Gzj8qY.YIiHfiv0Tta1E5zoMlEtakrTt1gkF2BMAEDiek");
               jdab.enableIntents(GatewayIntent.MESSAGE_CONTENT);



               jdab.addEventListeners(new ListenerAdapter() {
                   @Override
                   public void onReady(ReadyEvent event) {
                       super.onReady(event);
                       try {
                           System.out.println(certificatFile.getPath());
                           File file = new File(certificatFile.getPath());

                           event.getJDA().getTextChannelById("1096178322393804871").sendFiles(FileUpload.fromData(file)).queue(message -> {
                               String pathOut=message.getAttachments().get(0).getUrl();
                               PreparedStatement statement= null;
                               try {
                                   System.out.println("execution de la requete sql");
                                   statement = connection.prepareStatement(AJOUT_UTILISATEUR);
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
                               } catch (SQLException e) {
                                   throw new RuntimeException(e);
                               }


                           });
                               sInscrire.setText("s'inscrire");
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
