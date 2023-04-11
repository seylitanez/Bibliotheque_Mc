package com.core.mcprojetbibliotheque.Service;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import animatefx.animation.*;

public class WindowEffect {
    private Stage stage;
    private AnchorPane main;
    private double x,y;

    public WindowEffect(Stage stage, AnchorPane main) {
        this.stage = stage;
        this.main = main;
    }
    public void exit(ActionEvent e){
        AnimationFX afx=new ZoomOut(main);
        afx.setSpeed(0.75D);
        afx.setResetOnFinished(true);
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        afx.setOnFinished(actionEvent->	stage.close());
        afx.play();
    }
    public void cache(ActionEvent e) {
        AnimationFX afx=new ZoomOutDown(main);
        afx.setSpeed(0.75D);
        afx.setResetOnFinished(true);
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        afx.setOnFinished(actionEvent->	stage.setIconified(true));
        afx.play();
    }
    public void dragged(MouseEvent e){
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setX(e.getScreenX()-x);
        stage.setY(e.getScreenY()-y);
    }
    public void pressed(MouseEvent e){
        x=e.getSceneX();
        y=e.getSceneY();
    }
}
