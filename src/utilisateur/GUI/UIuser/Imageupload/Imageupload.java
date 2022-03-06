/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIuser.Imageupload;

import utilisateur.GUI.UIuser.Imageupload.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author malek
 */
public class Imageupload extends Application {
    
       Parent UploadIm;
       private double xOffset = 0; 
       private double yOffset = 0;
       
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        UploadIm=FXMLLoader.load(getClass().getResource("Imageupload.fxml"));

        // Pour deplacer la fenetre
        UploadIm.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        UploadIm.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    
        Scene Upload_scene = new Scene(UploadIm);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(Upload_scene);
        primaryStage.show();
        
    }
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }    
}
