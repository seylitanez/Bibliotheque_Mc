package com.core.mcprojetbibliotheque.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Login {
    private Stage stage;
    public void exit(ActionEvent e) {
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
        System.out.println("exit");
    }
}