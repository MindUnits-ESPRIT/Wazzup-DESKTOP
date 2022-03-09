/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.Vtoken;

import utilisateur.GUI.signup.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author malek
 */
public class tokeninterface extends Application {
    
       Parent token;
       private double xOffset = 0; 
       private double yOffset = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        token=FXMLLoader.load(getClass().getResource("Vtoken.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
                // Pour deplacer la fenetre
       token.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        token.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene token_scene = new Scene(token);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Activation compte");
        primaryStage.setScene(token_scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
