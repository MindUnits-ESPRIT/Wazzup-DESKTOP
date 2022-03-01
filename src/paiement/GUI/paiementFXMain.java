package paiement.gui;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//@author mouhi
public class paiementFXMain  extends Application {    
     Parent root;
    @Override
    public void start(Stage primaryStage) {
      

        try {
            root = FXMLLoader.load(getClass().getResource("paiementFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(paiementFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Wazzup");
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