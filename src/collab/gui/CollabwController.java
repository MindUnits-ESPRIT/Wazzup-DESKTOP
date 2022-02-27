package collab.gui;
import collab.entities.Salle_Collaboration;
import collab.services.CollabService;
import collab.services.ProjetService;
import database.db;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilisateur.entities.utilisateur;
/**
 * FXML Controller class
 * @author mouhi
 */
public class CollabwController implements Initializable {
     Parent collab_page_parent;
     Parent collab_page_list;
     ArrayList<utilisateur> list = new ArrayList();
        // test DB Connexion
        db cnx = db.getInstance();
        CollabService cs = new CollabService();
        ProjetService ps = new ProjetService();
    @FXML
    private TextField nomid;
    public Label area1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
    }    
    @FXML
    private void add(ActionEvent event) {
        String name = nomid.getText();
         if (nomid.getText().isEmpty()){
        area1.setText("choissiser un nom");
        }
         else{
        Salle_Collaboration salle = new Salle_Collaboration(list, "http://example.com/" + name, name, 7);
        int r = cs.creer(salle, 7);
        if (r==1){
            try {       
               collab_page_parent = FXMLLoader.load(getClass().getResource("PCollab.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(collab_page_parent);      
            Stage CStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            CStage.hide();
            CStage.setScene(scene);
            CStage.show();
        }else {       
          area1.setText("Collaboration exist");              
        } } 
    }

    @FXML
    private void ListerCollabs(ActionEvent event) {          
            try {       
               collab_page_list = FXMLLoader.load(getClass().getResource("CollabListe.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(collab_page_list);      
            Stage LStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            LStage.hide();
            LStage.setScene(scene);
            LStage.show(); 
    }  
}