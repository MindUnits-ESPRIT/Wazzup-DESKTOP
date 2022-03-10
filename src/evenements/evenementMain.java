/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;

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
/**
 *
 * @author SRN
 */
public class evenementMain extends Application {
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) {
        try {
            stg=primaryStage;
            primaryStage.setResizable(false);
            primaryStage.setTitle("Wazzup");
            Parent root ;
            root=FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));
                    Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(evenementMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void changeScene(String fxml)throws IOException{
    Parent pane = FXMLLoader.load(getClass().getResource(fxml));
    stg.getScene().setRoot(pane);
    }
                /**Button btn = new Button();
             * btn.setText("Say 'Hello World'");
             * btn.setOnAction(new EventHandler<ActionEvent>() {
             * 
             * @Override
             * public void handle(ActionEvent event) {
             * System.out.println("Hello World!");
             * }
             * });
             * 
             * StackPane root = new StackPane();
             * root.getChildren().add(btn);**/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
