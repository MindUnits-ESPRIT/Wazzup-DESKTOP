/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import database.db;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paiement.entities.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import paiement.service.PaiementService;
/**
 * FXML Controller class
 *
 * @author Ahmed Guedri
 */
public class PaiementFXMLController implements Initializable {
         ArrayList<paiement> list = new ArrayList();

    db cnx = db.getInstance();
    PaiementService ps = new PaiementService();
    
      
   @FXML
    private AnchorPane scenePane;

    @FXML
    private VBox otherback;

    @FXML
    private VBox viewbackgris;

    @FXML
    private ImageView cardcredit;

    @FXML
    private Label label;

    @FXML
    private ComboBox methode_paiement;

    @FXML
    private TextField prix;

    @FXML
    private Label prixtext;

    @FXML
    private Pane border;

    @FXML
    private ImageView icon;

    @FXML
    private Button exitbutton;

    @FXML
    private Button payer;

Stage stage;

    @FXML
    void Select(ActionEvent event) {
         String s = methode_paiement.getSelectionModel().getSelectedItem().toString();
        label.setText(s);
    }
    @FXML
    public void Exit(ActionEvent event) {
         stage = (Stage) scenePane.getScene().getWindow();
         stage.close();
    }
   @FXML
void payer(ActionEvent event) throws IOException{
    //Stage mainWindow = (Stage)scenePane.getScene().getWindow();

    String Prix = prix.getText();
    
    

PaiementService ps=new PaiementService();
String s = methode_paiement.getSelectionModel().getSelectedItem().toString();
float p=Float.parseFloat(Prix);  

 
paiement p1=new paiement( s,p);
      ps.ajouter(p1);
       System.out.println("add Done");
       Stage primaryStage= new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("StripeFXML.fxml"));
       primaryStage.setScene(new Scene(root,1080,720));
       primaryStage.setTitle("payer");
       primaryStage.show();
       
       
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          ObservableList<String> list = FXCollections.observableArrayList("Stripe","Credit_card");
         methode_paiement.setItems(list);
    }    
    
}
