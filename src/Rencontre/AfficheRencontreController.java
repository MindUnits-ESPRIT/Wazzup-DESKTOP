/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rencontre;

import Rencontre.entities.Rencontre;
import Rencontre.services.RencontreService;
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
import static utils.SessionUser.getUser;

/**
 * FXML Controller class
 *
 * @author SRN
 */
public class AfficheRencontreController implements Initializable {

      @FXML
    private TableView<Rencontre> TableView;
 @FXML
    private TableColumn<Rencontre, Integer> ID_Ren;
    @FXML
    private TableColumn<Rencontre, String> Type;

    @FXML
    private TableColumn<Rencontre, String> url_invi;

    @FXML
    void Modify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRencontre.fxml"));
    Parent p = (Parent)loader.load();
    ModifierRencontreController mec = loader.getController();
         int SelecteedID = TableView.getSelectionModel().getSelectedIndex();   
int ID =  TableView.getSelectionModel().getSelectedItems().get(0).getID_Ren();
        System.out.println("hetha el id  "+ID);
ModifierRencontreController me = new ModifierRencontreController();
me.initData(ID);
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(p);
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

    @FXML
    void Supprimer(ActionEvent event) {

        
        int SelecteedID = TableView.getSelectionModel().getSelectedIndex();

int ID =  TableView.getSelectionModel().getSelectedItems().get(0).getID_Ren();
         RencontreService es = new RencontreService();
                    es.supprimer(ID);
                    TableView.getItems().remove(SelecteedID);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      List<Rencontre> list = new ArrayList();
     RencontreService es = new RencontreService();

//list = es.afficher(19);
//es.afficher(19);
   showRencontre();
    }    
     public ObservableList<Rencontre> getRencontreList(){
 
      RencontreService es = new RencontreService();
    ObservableList<Rencontre> obs =  es.afficher(getUser().getID_Utilisateur());
     return obs;
     }
    public void showRencontre(){
         ObservableList<Rencontre> list = getRencontreList();
         ID_Ren.setCellValueFactory(new PropertyValueFactory<>("ID_Ren"));
   Type.setCellValueFactory(new PropertyValueFactory<>("Type_Rencontre"));
   url_invi.setCellValueFactory(new PropertyValueFactory<>("URL_Invitation"));
  TableView.setItems(list); 
   
}
    
}
