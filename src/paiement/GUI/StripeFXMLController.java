/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Ahmed Guedri
 */
public class StripeFXMLController implements Initializable {
  
    @FXML
    private AnchorPane scenePane;

    @FXML
    private VBox vb;

    @FXML
    private WebView webv;
    Stage stage;

    @FXML
    private Button quiter;
    private WebEngine engine;
   /* @FXML
    private Label factureici;*/
    public void initialize(URL arg0, ResourceBundle arg1) {
		
		engine = webv.getEngine();
		loadPage();
	}
    
     public void Exit(ActionEvent event) {
         stage = (Stage) scenePane.getScene().getWindow();
         stage.close();
    }
    public void loadPage() {
		engine.load("https://buy.stripe.com/test_fZeaFI0uf3FR6Z23cc");
	}
    

}