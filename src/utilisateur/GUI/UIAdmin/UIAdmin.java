/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIAdmin;

import utilisateur.GUI.UIuser.*;
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
public class UIAdmin extends Application {
    
       Parent UI_admin;
       
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        UI_admin=FXMLLoader.load(getClass().getResource("UIAdmin.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        // Pour deplacer la fenetre
        Scene UI_scene = new Scene(UI_admin);
        primaryStage.setResizable(true);
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
