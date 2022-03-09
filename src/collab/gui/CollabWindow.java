package collab.gui;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//@author mouhi
public class CollabWindow extends Application {    
     Parent root;
            private double xOffset = 0; 
       private double yOffset = 0;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            root = FXMLLoader.load(getClass().getResource("Collabw.fxml"));
                    primaryStage.initStyle(StageStyle.TRANSPARENT);
        // Pour deplacer la fenetre
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        } catch (IOException ex) {
            Logger.getLogger(CollabWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        Scene scene = new Scene(root); 
        primaryStage.setTitle("Wazzup Collaborations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
