/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIuser;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.SessionUser;


/**
 *
 * @author malek
 */
public class UIuser extends Application {
    
       Parent UI;
       private double xOffset = 0; 
       private double yOffset = 0;
       
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        UI=FXMLLoader.load(getClass().getResource("UIuser.fxml"));

        // Pour deplacer la fenetre
        UI.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        UI.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    
        Scene UI_scene = new Scene(UI);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(UI_scene);
        primaryStage.show();
        
    }
    
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }    
}
