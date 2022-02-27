/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collab.gui;

import collab.services.CollabService;
import collab.services.ProjetService;
import database.db;
import java.io.IOException;
import java.net.URL;
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
import utilisateur.entities.utilisateur;
//@author mouhi
public class PCollabController implements Initializable {
Parent collab_page_home;
    @FXML
    private Button retour;
    ObservableList<utilisateur> users = FXCollections.observableArrayList();
      db cnx = db.getInstance();
        CollabService cs = new CollabService();
        ProjetService ps = new ProjetService();
    @FXML
    public TableView<utilisateur> tab;
    @FXML
    public TableColumn<utilisateur, String> nom;
    @FXML
    private Button deleteC;
    @FXML
    private Label errarea;
    @FXML
    public TableColumn<utilisateur, String> idm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        users = cs.afficherCollab_Membres(63);     
        ObservableList<utilisateur> listo = (ObservableList<utilisateur>) users ;
        nom.setCellValueFactory(new PropertyValueFactory<utilisateur , String>("nom")); 
        idm.setCellValueFactory(new PropertyValueFactory<utilisateur , String>("ID_Utilisateur"));
        tab.setItems(listo); 
    }    
    @FXML
    private void returnb(ActionEvent event) {
         try {       
               collab_page_home = FXMLLoader.load(getClass().getResource("CollabListe.fxml"));
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
            errarea.setText("Selectionner un membre");
            
        }
        else {
        cs.supprimer_membre(63,tab.getSelectionModel().getSelectedItems().get(0).getID_Utilisateur());
        }
    }
    
}
