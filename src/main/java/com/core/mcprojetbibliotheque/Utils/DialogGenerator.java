package com.core.mcprojetbibliotheque.Utils;

import com.core.mcprojetbibliotheque.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class DialogGenerator extends Stage {
    private FXMLLoader fxmll;
    public DialogGenerator(String fxml) {
        try {
            fxmll=new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene= new Scene(fxmll.load());
            setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            centerOnScreen();
            initStyle(StageStyle.TRANSPARENT);
            initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e1) {}
    }
    public void build() {
        show();
    }
    public FXMLLoader getFxmll() {
        return fxmll;
    }
}
