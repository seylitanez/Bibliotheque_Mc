package com.core.mcprojetbibliotheque.Model;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.Scanner;

public class Commandes extends ListenerAdapter {
    String path;
    @Override
    public void onReady(ReadyEvent event) {
        try(Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Commande: ");
                String code = sc.nextLine();
                if (code.equals("img")) {
                    File file = new File(sc.nextLine());
                    event.getJDA().getTextChannelById("1096178322393804871").sendFiles(FileUpload.fromData(file)).queue(message -> {
                        System.out.println(message.getAttachments().get(0).getUrl());
                    });
                }
            }
        }
    }
}


