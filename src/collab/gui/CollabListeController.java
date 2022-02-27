package collab.gui;
import collab.entities.Salle_Collaboration;
import collab.services.CollabService;
import collab.services.ProjetService;
import com.gluonhq.charm.glisten.control.TextField;
import database.db;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import org.json.JSONArray;
import utilisateur.entities.utilisateur;
import utils.mail;
import static utils.mail.prepareMessage;
// @author mouhi
public class CollabListeController implements Initializable {
 Parent collab_page_home;
     Parent collab_page_list;
     Parent P_collab;
     List<Salle_Collaboration> list = new ArrayList();
        // test DB Connexion
        db cnx = db.getInstance();
        CollabService cs = new CollabService();
        ProjetService ps = new ProjetService();
    @FXML
    public TableView<Salle_Collaboration> tab;
    @FXML
    public TableColumn<Salle_Collaboration, String> nom;
    @FXML
    public TableColumn<Salle_Collaboration, String> urli;
    public TableColumn<utilisateur, String> membre;
    @FXML
    private Button retour;
    @FXML
    private Button deleteC;
    @FXML
    private Button UpdateC;
    @FXML
    private TextField nomC;
    @FXML
    private TextField userid;
    @FXML
    private Button AddM;
    @FXML
    public Label errarea;
    @FXML
    private Button acces;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = cs.afficher(7);     
        ObservableList<Salle_Collaboration> listo = (ObservableList<Salle_Collaboration>) list ;
        nom.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("Nom_Collab"));
        urli.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("URL_Collab"));
      
        tab.setItems(listo);   
    }    
    @FXML
    private void returnb(ActionEvent event) {
         try {       
               collab_page_home = FXMLLoader.load(getClass().getResource("Collabw.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(collab_page_home);      
            Stage CStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            CStage.hide();
            CStage.setScene(scene);
            CStage.show();
    }
    @FXML
    private void DeleteC(ActionEvent event) {  
        
        if (tab.getSelectionModel().getSelectedItems().isEmpty())
        {
            errarea.setText("il faut choisir une collaboration");
            
        }
        else {
        cs.supprimer(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab());
        }
    }
    @FXML
    private void UpdateC(ActionEvent event) {
        if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
        errarea.setText("il faut choisir une collaboration");
        }
        else {
        String nom = tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab();
        String nnom = nomC.getText() ;
        if ( nnom.isEmpty()){
        errarea.setText("il faut donner un nom");
        }
        else {
        cs.modifier(nom, nnom);
    }}}
    @FXML
    private void AddM(ActionEvent event) {    
         if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
        errarea.setText("il faut choisir une collaboration");
        }
         else {
        if (userid.getText().isEmpty()){
        errarea.setText("il faut donner un id");
        }
        else {
        int uid = Integer. parseInt(userid.getText());  
        Salle_Collaboration s = cs.getSalleInfo(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab());
        int r = cs.ajouter_membre(s.getID_Collab(), uid);
        if (r==1){
            
        utilisateur user = cs.getinfo(uid);
        String txt = "Bonjour " +user.getNom()+ "\n Vous etes invite a la collaboration "+tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab() ;
        String sub = "Invitation a une collaboration";
        String destinataire = user.getEmail();
        mail m = new mail(txt,sub,destinataire);   
        Message msg = prepareMessage(m.getSession(),m.getMail(),destinataire,txt,sub);
        System.out.println(msg);
            try {
                Transport.send(msg);
            } catch (MessagingException ex) {
                Logger.getLogger(CollabListeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("MAIL ENVOYEE");
        
        }
        else{
        errarea.setText("utilisateur n'exist pas");
        }}
    } }

    @FXML
    private void access(ActionEvent event) {
         if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
        errarea.setText("il faut choisir une collaboration");
        }
         else {
              try {       
               P_collab = FXMLLoader.load(getClass().getResource("Pcollab.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(P_collab);      
            Stage CStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            CStage.hide();
            CStage.setScene(scene);
            CStage.show();
       
        }
    }
    
}
