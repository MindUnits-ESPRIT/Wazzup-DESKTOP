package evenements;

import evenements.entities.evenements;
import evenements.services.evenementsService;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilisateur.entities.utilisateur;
import static utils.SessionUser.getUser;

public class AfficherEvenementController implements Initializable{
 
@FXML
    private TableView<evenements> ListView;

    @FXML
    private TableColumn<evenements, Integer> C1;

    @FXML
    private TableColumn<evenements, String> C2;

    @FXML
    private TableColumn<evenements,Integer> C3;

    @FXML
    private TableColumn<evenements, String> C4;

    @FXML
    private TableColumn<evenements, String> C5;

    @FXML
    private TableColumn<evenements, String> C6;

    @FXML
    private TableColumn<evenements, String> C7;

    @FXML
    private TableColumn<evenements, String > C8;

    @FXML
    private Button ModifEvent;

    @FXML
    private Button AddEvent;
    
    @FXML
    private Button supprimEvent;
      @FXML
    private Button AffichSalleCinema;

    @FXML
    private Button Rencontre;
  public AfficherEvenementController(){
      
      
    }
   
//    @FXML
//    void DeleteEvent(ActionEvent event) {
//        int SelecteedID = ListView.getSelectionModel().getSelectedIndex();
//                  ListView.getItems().remove(SelecteedID);
//              int ID =  ListView.getItems().remove(SelecteedID).getID_Event();
//                evenementsService es = new evenementsService();
//                    es.supprimer(ID);
//    }
      @FXML
    void AddEvnet(ActionEvent event) throws IOException {
        evenementsService e = new evenementsService();
    Parent root=FXMLLoader.load(getClass().getResource("evenementFXML.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @FXML
    void ModifyEvent(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("Modifierevenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }
public ObservableList<evenements> getEvenementList(){
 
      evenementsService es = new evenementsService();
    ObservableList<evenements> obs =  es.afficher(getUser().getID_Utilisateur());
  
    return obs;
}
public void showEvents(){
   
            ObservableList<evenements> list = getEvenementList();
C1.setCellValueFactory(new PropertyValueFactory<>("ID_Event"));
C2.setCellValueFactory(new PropertyValueFactory<>("Nom_Event"));
C3.setCellValueFactory(new PropertyValueFactory<>("Nbr_participants"));
C4.setCellValueFactory(new PropertyValueFactory<>("Date_Event"));
C5.setCellValueFactory(new PropertyValueFactory<>("Type_Event"));
C6.setCellValueFactory(new PropertyValueFactory<>("Event_Visibilite"));
C7.setCellValueFactory(new PropertyValueFactory<>("Description"));
C8.setCellValueFactory(new PropertyValueFactory<>("Date_P"));
ListView.setItems(list);                                                                     // ajouter les elements tableView
}
    
@Override
public void initialize(URL url, ResourceBundle rb) {
              utilisateur user = new utilisateur();
              user = getUser();
List<evenements> list = new ArrayList();
     evenementsService es = new evenementsService();

list = es.afficher(user.getID_Utilisateur());
   showEvents();
     //es.afficher(5);
    
}

    @FXML
    private void DeleteEvent(ActionEvent event) {
        int SelecteedID = ListView.getSelectionModel().getSelectedIndex();

int ID =  ListView.getSelectionModel().getSelectedItems().get(0).getID_Event();
         evenementsService es = new evenementsService();
                    es.supprimer(ID);
                    ListView.getItems().remove(SelecteedID);
    }
    @FXML
    void AffichSalle(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("../SalleCinema/afficherSalleCinema.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @FXML
    void AfficherRencontre(ActionEvent event) throws IOException {
    Parent root=FXMLLoader.load(getClass().getResource("../Rencontre/AfficheRencontre.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

}
