package com.core.mcprojetbibliotheque.Utils;

import com.core.mcprojetbibliotheque.Controller.ItemControleur;
import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Model.Abonne;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ListAdapter {
    private Node scenes;
    private ArrayList<Abonne> abonnes;
    private VBox vBox;
    public ListAdapter(VBox vBox, ArrayList<Abonne> abonnes) {
        this.abonnes = abonnes;
        this.vBox = vBox;
    }
    public void build() {
        for (Abonne abonne:abonnes) {
            try {
                FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("ListItem.fxml"));
                scenes=loader.load();
                ItemControleur controller=loader.getController();
                controller.setitem(abonne);
                vBox.getChildren().add(scenes);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
    public void clear() {
        vBox.getChildren().clear();
    }
    public void update() {
        clear();
        build();
    }

    public ArrayList<Abonne> getAbonnes() {
        return abonnes;
    }

    public void setAbonnes(ArrayList<Abonne> abonnes) {
        this.abonnes = abonnes;
        update();
    }
}
