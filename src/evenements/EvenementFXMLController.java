/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;

import Rencontre.entities.Rencontre;
import Rencontre.services.RencontreService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import evenements.entities.evenements;
import evenements.services.evenementsService;
import java.io.IOException;
import evenements.URL.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static utils.SessionUser.getUser;
import SalleCinema.Salle_Cinema_FXMLController;
/**
 * FXML Controller class
 *
 * @author SRN
 */
public class EvenementFXMLController implements Initializable {
 /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        ListType.getItems().addAll(types);
//        Visibilite.getItems().addAll(Vis);
//    }  
     
    @FXML 
    private Button b1;
    @FXML 
    private Button b2;
    @FXML
    private Label NameEvent;
    @FXML
    private Label nbr_p;
    @FXML
    private Label typeEvent;
    @FXML 
    private Label Visibility;
    @FXML 
    private TextArea Description;
    @FXML 
   private ChoiceBox<String> Visibilite;
    private String[] Vis ={"Salle_publique","Salle_privee"};
    @FXML
    private ChoiceBox<String> ListType;
    private String[] types ={"SalleCinema","Rencontre"};
    @FXML
    private JFXTextField NombreParticipant;
    @FXML 
    private JFXTextField NmEvent;
    @FXML 
    private JFXDatePicker Date_Event;
    @FXML
    private Label Date;
     
        
    public EvenementFXMLController(){
       
    }
    public void CreateEvent(ActionEvent event) throws IOException {
        if (CheckFields()==true){
         System.out.println("CLICKED11");
           evenementsService es = new evenementsService();
         evenements e =new evenements(getUser().getID_Utilisateur(),NmEvent.getText(),Integer.parseInt(NombreParticipant.getText()),(Date_Event.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),ListType.getValue(),Visibilite.getValue(),Description.getText());    
        es.ajouter(e);
            System.out.println("3amek el evnt"+e.getID_Event());
    
        if(ListType.getValue().matches("SalleCinema") ){
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation( getClass().getResource("../SalleCinema/Salle_Cinema_FXML.fxml"));
                Parent p = (Parent)loader.load();
                Salle_Cinema_FXMLController sc = loader.getController();   
                sc.initData(e.getID_Event());
                System.out.println("traaa nchouf"+e.getID_Event());
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(p);
stage.setScene(scene);
stage.show();

             }
        else  if(ListType.getValue().matches("Rencontre") && Visibilite.getValue().matches("Salle_privee")){
 Parent root=FXMLLoader.load(getClass().getResource("../Rencontre/RencontreFXML.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
RencontreService rs=new RencontreService();
URL u = new URL();
Rencontre r = new Rencontre("Virtuel",u.GetUrl_Rencontre(getUser().getID_Utilisateur()));
evenements ev = new evenements(e.getID_Event());
evenements ev = new evenements(67);
rs.ajouter(r, ev);
        }
        else if(ListType.getValue().matches("Rencontre") && Visibilite.getValue().matches("Salle_publique")){
            Parent root=FXMLLoader.load(getClass().getResource("../evenements/afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
        }

        }
             
//             if(ListType.getValue().equals("Rencontre") ){
//        Parent root=FXMLLoader.load(getClass().getResource("SalleCinema/Salle_Cinema_FXML.fxml"));
// Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//Scene scene = new Scene(root);
//stage.setScene(scene);
//stage.show();
//        }
//             
        
    }
    public boolean CheckFields() throws IOException{
     boolean validNom=true;
     boolean validnbr=true;
     boolean ListT=true;
     boolean Visi=true;
     boolean check=false;
     boolean date=true;
       Date dt=new Date();
        int year=dt.getYear()+1900;
        
        if (NmEvent.getText().isEmpty() ){
        NameEvent.setText("Veuillez Ajouter le nom ");
        validNom = false;
        }
        
         NombreParticipant.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                NombreParticipant.setText(newValue.replaceAll("[^\\d]", ""));
                nbr_p.setText("Chiffre!");
            }
            });
        if (NombreParticipant.getText().isEmpty()){
            nbr_p.setText("Veuillez Ajouter le nombre ");
            validnbr=false;
        }
       
     
//        else if (!NombreParticipant.getText().matches("[0-9]*")){
//            nbr_p.setText("Chiffre!");
//            validnbr=false;
//        }
        if (ListType.getValue()==null){
        typeEvent.setText("Veuillez choisir le type");
        ListT=false;
        }
        if (Visibilite.getValue()==null){
        Visibility.setText("Veuillez choisir la visibilit??");
        Visi=false;
        }
        if(Date_Event.getValue()==null){
          Date.setText("Veuillez choisir la date");
                    }
        else if(Date_Event.getValue().getYear()<year){
            date =false;    
            Date.setText("invalid");
        }
        if (validNom && validnbr && ListT && Visi && date)
            return check = true;
        return check;
       
        }
        
    
     @FXML
    void RetourPage(ActionEvent event) throws IOException {
 Parent root=FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        ListType.getItems().addAll(types);
         Visibilite.getItems().addAll(Vis);
    }
}
