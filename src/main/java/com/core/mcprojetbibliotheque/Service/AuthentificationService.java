package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Utils.Commandes;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.File;

public class AuthentificationService {
    Commandes commandes;
    public String inscription(String nom,String prenom,String username,String email,String password,String categorie,File certificatFile) throws InterruptedException {

        commandes=new Commandes(certificatFile.getPath());
       Thread pictureRegesterTh=new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("chargement...");
               JDABuilder jdab= JDABuilder.createDefault("MTA5NjE3NzczMzkzNzEzNTc0Ng.Gzj8qY.YIiHfiv0Tta1E5zoMlEtakrTt1gkF2BMAEDiek");
               jdab.enableIntents(GatewayIntent.MESSAGE_CONTENT);



               jdab.addEventListeners(commandes);
               jdab.setStatus(OnlineStatus.ONLINE);
               JDA jda=jdab.build();
               jda.updateCommands();

               System.out.println(nom);
               System.out.println(prenom);
               System.out.println(email);
               System.out.println(password);
               System.out.println(username);

           }
       });
       pictureRegesterTh.start();




       return commandes.getPathOut();


    }
}
