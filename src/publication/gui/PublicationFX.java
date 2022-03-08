/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

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
import javafx.stage.StageStyle;
import utilisateur.entities.utilisateur;

/**
 *
 * @author Misow3002
 */
public class PublicationFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root;
            root=FXMLLoader.load(getClass().getResource("PublicationInterface.fxml"));
              // root=FXMLLoader.load(getClass().getResource("Emoji.fxml"));
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);

primaryStage.setTitle("Publication BETA");
primaryStage.setScene(new Scene(root,1080,720));
primaryStage.show();
        } catch (IOException ex) 
        {
            Logger.getLogger(PublicationFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
