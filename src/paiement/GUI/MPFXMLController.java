/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import facture.services.factureService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import offre_publicitaire.service.OffreService;
import paiement.entities.paiement;
import paiement.service.PaiementService;
import utilisateur.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class MPFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private AnchorPane AnchorPane;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private VBox VBoxCenter;

    @FXML
    private Button buttonpayer;

    @FXML
    private TextField prixtext;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private ImageView imgcard;

    @FXML
    private VBox VBoxBot;

    @FXML
    private Text textdev;

    @FXML
    private ImageView logomind;

    @FXML
    private VBox VBoxRight;

    @FXML
    private ImageView img2;

    @FXML
    private VBox VBoxLeft;

    @FXML
    private ImageView img1;
    @FXML
void payer(ActionEvent event) throws IOException{
     //Stage mainWindow = (Stage)scenePane.getScene().getWindow();
        String Prix = prixtext.getText();
    
    

    PaiementService ps=new PaiementService();
UtilisateurService us=new UtilisateurService();
OffreService os=new OffreService();
factureService fs=new factureService();
String s = combobox.getSelectionModel().getSelectedItem();
float p=Float.parseFloat(Prix);  
String output = (String) combobox.getValue();

paiement p1=new paiement( s,p);


/*facture f1=new facture(".PDF");
utilisateur u1 = new utilisateur(19,"spx","tn");
offre_publicitaire o = new offre_publicitaire(8);
      
      fs.ajouter(f1, u1, o, p1);*/
     
       
       if ("Stripe".equals(output)){ 
//-----------------------------------Stripe---------------------------------------------------
 ps.ajouter(p1);
       System.out.println("add Done");
       Stage primaryStage= new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("StripeFXML.fxml"));
       primaryStage.setScene(new Scene(root,794,640));
       primaryStage.setTitle("payer");
       primaryStage.show();
       }else if("Credit_card".equals(output)){
 //-----------------------------------Paypal---------------------------------------------------
  ps.ajouter(p1);
       System.out.println("add Done");
      Stage primaryStage= new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("paypalFXML.fxml"));
       primaryStage.setScene(new Scene(root,794,640));
       primaryStage.setTitle("payer");
       primaryStage.show();
       }else{
               {
    System.out.println("alerted");
    Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("ERROR");
                alert.setContentText("Paiement introuvable veuillez sélectionner votre methode de paiement");
		alert.showAndWait();
               
               
               }
}
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Stripe","Credit_card");
         combobox.setItems(list);
    }    
    
}
