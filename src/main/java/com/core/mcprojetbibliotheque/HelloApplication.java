package com.core.mcprojetbibliotheque;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    	try {

        	FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("empruntEnRetard.fxml"));//abonnesReservatinList.fxml  espaceLivre.fxml 
        	//AbonneListEmprunt.fxml
        	//DemandeProlongé.fxml
        	//GestioneDesUtilisateur.fxml
            Scene scene = new Scene(fxmlLoader.load());
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    public static void main(String[] args) {
        launch();
        
    }
}