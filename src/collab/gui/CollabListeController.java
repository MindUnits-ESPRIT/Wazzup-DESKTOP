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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import utilisateur.entities.utilisateur;
import utilisateur.services.UtilisateurService;
import utils.mail;
import static utils.mail.prepareMessage;
// @author mouhi
public class CollabListeController implements Initializable {
    int iduser =23;
 Parent collab_page_home;
     Parent collab_page_list;
     Parent P_collab;
     List<Salle_Collaboration> list = new ArrayList();
        // test DB Connexion
        db cnx = db.getInstance();
        CollabService cs = new CollabService();
        ProjetService ps = new ProjetService();
        UtilisateurService us = new UtilisateurService();
        
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
    private TextField userid;
    @FXML
    private Button AddM;
    @FXML
    public Label errarea;
    @FXML
    private Button acces;
    @FXML
    private ComboBox<String> usertoadd;
    @FXML
    private TableColumn<Salle_Collaboration, String> idcu;
    @FXML
    private Button annuler;
    @FXML
    private Button confirmer;
    @FXML
    private SplitPane confirmpanel;
    int detect =0;
    @FXML
    private AnchorPane ANP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //afficher la liste des collaboration du l'utilisateur active    
        list = cs.afficher(iduser);     
        ObservableList<Salle_Collaboration> listo = (ObservableList<Salle_Collaboration>) list ;
        nom.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("Nom_Collab"));
        urli.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("URL_Collab")); 
        idcu.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("ID_Utilisateur"));
        tab.setItems(listo);
        
       
      
        
    }    
    @FXML
    // button de retour au scene precedent
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
            CStage.show(); }
    @FXML
    // suppression d'un collab
    private void DeleteC(ActionEvent event) {  
        
        if (tab.getSelectionModel().getSelectedItems().isEmpty()){
            errarea.setText("il faut choisir une collaboration"); }
        else {
            Salle_Collaboration s = cs.getSalleInfo(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab());
            if (s.getID_Utilisateur()!=iduser){
          cs.Notificationmanager(8);
        }
        else {
             
            detect =2;
            ANP.setVisible(true);
         
              
           }     
         }}
    @FXML
    // update d'un collab
    private void UpdateC(ActionEvent event) {
        if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
        errarea.setText("il faut choisir une collaboration");}
        else {
            Salle_Collaboration s = cs.getSalleInfo(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab());
             if (s.getID_Utilisateur()!=iduser){
          cs.Notificationmanager(8);
        }
        else {
        String nomi = tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab();
        String nnom = nomC.getText() ;
        if ( nnom.isEmpty()){
        errarea.setText("il faut donner un nom"); }
        else {
            detect = 1;
            ANP.setVisible(true);
         }}}}
    @FXML
    // ajout d'un membre a un collab
    private void AddM(ActionEvent event) { 
        errarea.setText("");
        if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
        errarea.setText("il faut choisir une collaboration"); }
        else {      
             Salle_Collaboration s1 = cs.getSalleInfo(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab());
             if (s1.getID_Utilisateur()!=iduser){
          cs.Notificationmanager(8);
        }
        else {
        if (usertoadd.getSelectionModel().getSelectedItem() == null){
            errarea.setText("il faut choisir un membre");   
            ObservableList<utilisateur> listu = cs.afficherMembresNotInCollab(tab.getSelectionModel().getSelectedItems().get(0).getID_Collab()) ;   
                    usertoadd.getItems().clear();
               listu.forEach((utilisateur u) -> {
                    usertoadd.getItems().add(u.getID_Utilisateur()+" "+u.getPrenom()+" "+u.getNom());       
               }); 
        }
        else {
        String uid = usertoadd.getSelectionModel().getSelectedItem().toString();
             usertoadd.getItems().clear();
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
        cs.Notificationmanager(0);
        }
        else{
        errarea.setText("utilisateur n'exist pas");
        }
        }
        }}}
// acces a un collab choisi
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
             Salle_Collaboration s = cs.getSalleInfo(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab()); 
 // envoi le collab choisi comme un paramtere au suivante scene            
             CStage.setUserData(s);
             CStage.hide();
             CStage.setScene(scene);
             CStage.show();
       
        }
    }



    @FXML
    private void drowpdown(MouseEvent event) {
         errarea.setText("");
          if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
        errarea.setText("il faut choisir une collaboration"); }
        else {      
          ObservableList<utilisateur> listu = cs.afficherMembresNotInCollab(tab.getSelectionModel().getSelectedItems().get(0).getID_Collab()) ;   
                    usertoadd.getItems().clear();
               listu.forEach((utilisateur u) -> {
                    usertoadd.getItems().add(u.getID_Utilisateur()+" "+u.getPrenom()+" "+u.getNom());       
               }); 
    }
    
}

    @FXML
    private void Annuler(ActionEvent event) {
        detect =0;
         ANP.setVisible(false);
         nomC.setText("");
    }

    @FXML
    private void Confirmer(ActionEvent event) {
        if(detect == 1){
            detect = 0;
        String nomi = tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab();
        String nnom = nomC.getText() ;
        cs.modifier(nomi, nnom);
        cs.Notificationmanager(2);
        list = cs.afficher(iduser);     
        ObservableList<Salle_Collaboration> listo = (ObservableList<Salle_Collaboration>) list ;
        nom.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("Nom_Collab"));
        urli.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("URL_Collab"));
        tab.setItems(listo);
        ANP.setVisible(false);
        nomC.setText("");
        
        }else
        {if(detect ==2){
            detect = 0;
            cs.supprimer(tab.getSelectionModel().getSelectedItems().get(0).getNom_Collab());
        cs.Notificationmanager(1);
        list = cs.afficher(iduser);     
        ObservableList<Salle_Collaboration> listo = (ObservableList<Salle_Collaboration>) list ;
        nom.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("Nom_Collab"));
        urli.setCellValueFactory(new PropertyValueFactory<Salle_Collaboration , String>("URL_Collab"));
        tab.setItems(listo);
        ANP.setVisible(false);
        nomC.setText("");}}
            
        
    }


}
