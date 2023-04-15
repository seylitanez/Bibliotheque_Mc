package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import animatefx.animation.*;

public class WindowEffect {
    private Stage stage;
    private Parent scene;
    private AnchorPane main;
    private double x,y;

    public WindowEffect(AnchorPane main) {
        this.main = main;
    }
    //fermer la fenetre
    public void exit(ActionEvent e){
        //dezoum
        AnimationFX afx=new ZoomOut(main);
        afx.setSpeed(0.75D);
        afx.setResetOnFinished(true);
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        afx.setOnFinished(actionEvent->	stage.close());
        afx.play();
    }
    //reduire la fentre
    public void cache(ActionEvent e) {
        //dezoom et doun
        AnimationFX afx=new ZoomOutDown(main);
        afx.setSpeed(0.75D);
        afx.setResetOnFinished(true);
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        afx.setOnFinished(actionEvent->	stage.setIconified(true));
        afx.play();
    }
    //modifer la position de la fenetre
    public void dragged(MouseEvent e){
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setX(e.getScreenX()-x);
        stage.setY(e.getScreenY()-y);
    }
    //recupres la position du clic
    public void pressed(MouseEvent e){
        x=e.getSceneX();
        y=e.getSceneY();
    }
    //changer linterface d'affichage
    public void switchStage(ActionEvent e,String fxml) throws Exception{
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene= FXMLLoader.load(HelloApplication.class.getResource(fxml));
        stage.getScene().setRoot(scene);
        stage.show();
    }
}
