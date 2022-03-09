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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utilisateur.entities.utilisateur;
import static utils.SessionUser.getUser;
//@author mouhib
public class CollabwController implements Initializable {
    Parent home;
     int iduser = getUser().getID_Utilisateur();
     Parent collab_page_parent;
     Parent collab_page_list;
     ArrayList<utilisateur> list = new ArrayList();
     db cnx = db.getInstance();
     CollabService cs = new CollabService();
     ProjetService ps = new ProjetService();
       double xOffset = 0; 
          double yOffset = 0;
    @FXML
    private TextField nomid;
    public Label area1;
    @FXML
    private Button ret;
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
    }    
    @FXML
    //add collaboration fonction
    private void add(ActionEvent event) {
        
     
        String name = nomid.getText();
        if (nomid.getText().isEmpty()){
        area1.setText("choisissez un nom");
        }
         else{
        Salle_Collaboration salle = new Salle_Collaboration(list, "http://example.com/" + name, name, iduser);
        int r = cs.creer(salle, iduser);
        if (r==1){
            try {       
               collab_page_parent = FXMLLoader.load(getClass().getResource("PCollab.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(collab_page_parent);      
             Stage CStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
             Salle_Collaboration s = cs.getSalleInfo(nomid.getText()); 
             CStage.setUserData(s); 
             CStage.hide();
             CStage.setScene(scene);
             CStage.show();
             cs.Notificationmanager(3);
           
          
             collab_page_parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
                  collab_page_parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               CStage.setX(event.getScreenX() - xOffset);
            CStage.setY(event.getScreenY() - yOffset);
            }
        });
             
             
        }else {       
          area1.setText("Collaboration exist");              
        } } }
    @FXML
   //button afficher collab fonction 
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
             collab_page_list.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
                 collab_page_list.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               LStage.setX(event.getScreenX() - xOffset);
              LStage.setY(event.getScreenY() - yOffset);
            }
        });
    }  

    @FXML
    private void returnb(ActionEvent event) {
         try {       
              home = FXMLLoader.load(getClass().getResource("../../utilisateur/GUI/UIuser/UIuser.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(home);      
            Stage CStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            CStage.hide();
            CStage.setScene(scene);
            CStage.show();
           
          
              home.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
                  home.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               CStage.setX(event.getScreenX() - xOffset);
             CStage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}
