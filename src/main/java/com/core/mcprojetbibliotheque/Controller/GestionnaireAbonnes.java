package com.core.mcprojetbibliotheque.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

import com.core.mcprojetbibliotheque.Service.WindowEffect;




public class GestionnaireAbonnes implements Initializable {
	private WindowEffect effect;
	
	
	
	
	
	
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    		
    
 }
    
    public void Reservation (ActionEvent e) throws Exception {
    	
    	effect.switchStage(e,"abonnesReservatinList.fxml");
    	
    	
    	
    	
    }
 public void ReservationAccepté (ActionEvent e) throws Exception {
    	
    	effect.switchStage(e,"abonnesReservatinList.fxml");
    	
    	
    	
    	
    }
 public void ReservationRefusé (ActionEvent e) throws Exception {
 	
 	effect.switchStage(e,"abonnesReservatinList.fxml");
 	
 	
 	
 	
 }
    
    
    
}
