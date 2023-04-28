package com.core.mcprojetbibliotheque.Utils;

import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Service.PopupListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;

public class DialogGenerator extends Stage {

    private PopupListener popupListener;
    private FXMLLoader fxmll;
    public DialogGenerator(String fxml) throws IOException {
            fxmll=new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene=new Scene(fxmll.load());
            setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            centerOnScreen();
            initStyle(StageStyle.TRANSPARENT);
            initModality(Modality.APPLICATION_MODAL);
    }
    public DialogGenerator(String fxml,PopupListener popupListener) throws IOException {
            fxmll=new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene=new Scene(fxmll.load());
            setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            centerOnScreen();
            initStyle(StageStyle.TRANSPARENT);
            initModality(Modality.APPLICATION_MODAL);
        this.popupListener=popupListener;

    }
    public void build() {
        show();
    }
    public void build(DialogGenerator generator) {
        popupListener.onChargementListener(generator);
        show();
    }
    public FXMLLoader getFxmll() {
        return fxmll;
    }
}
