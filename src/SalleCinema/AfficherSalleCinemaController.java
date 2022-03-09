/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;

import SalleCinema.entities.SalleCinema;
import SalleCinema.services.SalleCinemaService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SRN
 */
public class AfficherSalleCinemaController implements Initializable {

     @FXML
    private TableView<SalleCinema> TaleView;

    @FXML
    private TableColumn<SalleCinema, String> Nom_Salle;


    @FXML
    private TableColumn<SalleCinema, String> Url_Salle;

    @FXML
    void ModifierSalle(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("../SalleCinema/SupprimerSalle.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("../evenements/afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }
//SupprimerSalle
    @FXML
    void SupprimerSalle(ActionEvent event) throws IOException {
int SelecteedID = TaleView.getSelectionModel().getSelectedIndex();

int ID =  TaleView.getSelectionModel().getSelectedItems().get(0).getID_Salle();
         SalleCinemaService es = new SalleCinemaService();
                    es.supprimer(ID);
                    TaleView.getItems().remove(SelecteedID);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      List<SalleCinema> list = new ArrayList();
     SalleCinemaService es = new SalleCinemaService();

list = es.afficher(19);
//es.afficher(19);
   showSalle();
    }    
     public ObservableList<SalleCinema> getSalleList(){
 
      SalleCinemaService es = new SalleCinemaService();
    ObservableList<SalleCinema> obs =  es.afficher(19);
     return obs;
     }
    public void showSalle(){
         ObservableList<SalleCinema> list = getSalleList();
   Nom_Salle.setCellValueFactory(new PropertyValueFactory<>("NomSalle"));
   Url_Salle.setCellValueFactory(new PropertyValueFactory<>("URL_Salle"));
  TaleView.setItems(list); 
   
}
    
}
