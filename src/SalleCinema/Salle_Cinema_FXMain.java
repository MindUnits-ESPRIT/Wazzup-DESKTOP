/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import evenements.*;


/**
 *
 * @author SRN
 */
public class Salle_Cinema_FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setResizable(false);
            primaryStage.setTitle("Wazzup");
            Parent root ;
           root=FXMLLoader.load(getClass().getResource("afficherSalleCinema.fxml")); 
//           root=FXMLLoader.load(getClass().getResource("APICinemaInfo.fxml"));
                    Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Salle_Cinema_FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
