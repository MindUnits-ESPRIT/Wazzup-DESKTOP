/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import evenements.entities.evenements;
import evenements.services.evenementsService;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SRN
 */
public class ModifierevenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Visi.getItems().addAll(Vis);
        TypeEvent.getItems().addAll(Type);
    }    
     @FXML
    private Button b2;

    @FXML
    private JFXTextField eventName;

    @FXML
    private JFXTextField nbrP;

    @FXML
    private JFXDatePicker dateEvent;

    @FXML
    private ChoiceBox<String> TypeEvent;
    private String[] Type ={"SalleCinema","Rencontre"};

    @FXML
    private TextArea Description;

    @FXML
    private Button b1;

    @FXML
    private Label WrongName;

    @FXML
    private Label WrongNbr;

    @FXML
    private Label WrongDate;

    @FXML
    private Label WrongType;
    @FXML
    private Label visibility;
    @FXML
    private ChoiceBox<String> Visi;
     private String[] Vis ={"Salle_publique","Salle_privee"};
     @FXML
     private Label WrongVis;
    public void ModifierEvent(ActionEvent event) throws IOException {
       if (CheckFields()==true){
         System.out.println("CLICKED11");
          evenementsService es = new evenementsService();
         evenements e =new evenements(5,eventName.getText(),Integer.parseInt(nbrP.getText()),(dateEvent.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),TypeEvent.getValue(),Visi.getValue(),Description.getText());
         
        es.modifier(14, eventName.getText(), Integer.parseInt(nbrP.getText()), (dateEvent.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), TypeEvent.getValue(), Visi.getValue(), Description.getText());
       }
    }
    public boolean CheckFields(){
     boolean validNom=true;
     boolean validnbr=true;
     boolean ListT=true;
     boolean visibi=true;
     boolean check=false;
     boolean date=true;
         Date dt=new Date();
        int year=dt.getYear()+1900;
        int month = dt.getMonth();
        if (eventName.getText().isEmpty()){
            WrongName.setText("Veuillez ajout le nom !");
            validNom=false;
        }
        nbrP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                nbrP.setText(newValue.replaceAll("[^\\d]", ""));
                WrongNbr.setText("Chiffre!");
            }
            });
       if (nbrP.getText().isEmpty()){
           WrongNbr.setText("Veuillez ajouter un nombre!");
           validnbr=false;
       }
       if (dateEvent.getValue()==null){
           WrongDate.setText("Veuillez ajouter date!");
           date=false;
       }
      else if((dateEvent.getValue().getYear()<year) || (dateEvent.getValue().getMonth().getValue()> month) ){
           WrongDate.setText("invalid");
           date=false;
       }
       if (TypeEvent.getValue()==null){
           WrongType.setText("Veuillez ajouter type!");
           ListT=false;
       }
       if(Visi.getValue()==null){
           WrongVis.setText("Veuillez ajouter visibility!");
           visibi=false;
       }
        if (visibi && ListT && date && validnbr && validNom){
            WrongNbr.setTextFill(Color.web("#00FFFF"));
            WrongNbr.setText("Confirmed");
            WrongName.setTextFill(Color.web("#00FFFF"));
             WrongName.setText("Confirmed");
            WrongVis.setTextFill(Color.web("#00FFFF"));
            WrongVis.setText("Confirmed");
            WrongDate.setTextFill(Color.web("#00FFFF"));
          WrongDate.setText("Confirmed");
            WrongType.setTextFill(Color.web("#00FFFF"));
       WrongType.setText("Confirmed");
            return check = true;}
        return check;
       
        
    }
     @FXML
    void Retour(ActionEvent event) throws IOException {
 Parent root=FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

            }
