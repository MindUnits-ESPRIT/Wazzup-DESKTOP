package collab.gui;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//@author mouhi
public class CollabWindow extends Application {    
     Parent root;
    @Override
    public void start(Stage primaryStage) {
        try {
            root = FXMLLoader.load(getClass().getResource("Collabw.fxml"));
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
