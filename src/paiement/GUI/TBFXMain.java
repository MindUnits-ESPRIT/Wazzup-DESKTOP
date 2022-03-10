/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SBS
 */
public class TBFXMain extends Application {
    
   Parent root;
    @Override
    public void start(Stage primaryStage) {
      

        try {
            root = FXMLLoader.load(getClass().getResource("TBFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TBFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root,794,640);
        
        primaryStage.setTitle("Wazzup");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
