/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class PaiementinterfaceController implements Initializable {
Stage stage;

    /**
     * Initializes the controller class.
     */
      @FXML
    private BorderPane BorderPane;

    @FXML
    private VBox VBoxTop;

    @FXML
    private ButtonBar ButtonBar;

    @FXML
    private Button ButtonRetour;

    @FXML
    private VBox VBoxLeft;

    @FXML
    private Label LabelWAZZUP;

    @FXML
    private ImageView LogoWAZZUP;

    @FXML
    private Button ButtonPaiement;

    @FXML
    private ImageView LogoPaiement;

    @FXML
    private Button ButtonFactures;

    @FXML
    private ImageView LogoFactures;

    @FXML
    private Button ButtonTableauBoard;

    @FXML
    private Label LabelCopyright;

    @FXML
    private ImageView LogoMindunits;

    @FXML
    private VBox VBoxCenter;

    @FXML
    private StackPane contentArea;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    public void Exit(ActionEvent event) {
         stage = (Stage) BorderPane.getScene().getWindow();
         stage.close();
    }
    
    Parent root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try{
        root =FXMLLoader.load(getClass().getResource("WhiteFXML.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
        }catch(IOException ex){
        
        java.util.logging.Logger.getLogger(PaiementinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        
        }     
    }    

    /**
     *
     * @param actionEvent
     * @throws java.io.IOException
     */
    public void MPFXML(javafx.event.ActionEvent actionEvent) throws IOException{
    
        
        root =FXMLLoader.load(getClass().getResource("MPFXML.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    
    public void StripeFXML(javafx.event.ActionEvent actionEvent) throws IOException{
    
        
        root =FXMLLoader.load(getClass().getResource("StripeFXML.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    public void factureFXML(javafx.event.ActionEvent actionEvent) throws IOException{
    
        
        root =FXMLLoader.load(getClass().getResource("factureFXML.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
        public void TBFXML(javafx.event.ActionEvent actionEvent) throws IOException{
    
        
        root =FXMLLoader.load(getClass().getResource("TBFXML.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    
   
}
