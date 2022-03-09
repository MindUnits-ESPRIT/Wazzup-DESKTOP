/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author malek
 */
public class authinterface extends Application {
    Parent root;
       private double xOffset = 0; 
       private double yOffset = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
            root=FXMLLoader.load(getClass().getResource("auth.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        // Pour deplacer la fenetre
       root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root,562,453);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Authentification");
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
