/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.signup;

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
public class signupinterface extends Application {
    
       Parent signup;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        signup=FXMLLoader.load(getClass().getResource("signup.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene Signup_scene = new Scene(signup);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Creation d'un compte");
        primaryStage.setScene(Signup_scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
