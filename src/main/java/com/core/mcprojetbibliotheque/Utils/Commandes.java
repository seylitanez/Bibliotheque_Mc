package com.core.mcprojetbibliotheque.Utils;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;

public class Commandes extends ListenerAdapter {
    private String pathIn;


    private String pathOut;

    public Commandes(String path) {
        this.pathIn = path;
    }
    public String getPathOut() {
        return pathOut;
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
                    System.out.println(pathIn);
                    File file = new File(pathIn);

                    event.getJDA().getTextChannelById("1096178322393804871").sendFiles(FileUpload.fromData(file)).queue(message -> {
                        pathOut=message.getAttachments().get(0).getUrl();
                        System.out.println(message.getAttachments().get(0).getUrl());

                    });
            System.out.println("file sent");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}


