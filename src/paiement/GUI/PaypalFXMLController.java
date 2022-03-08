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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class PaypalFXMLController implements Initializable {
    @FXML
    private AnchorPane anchor;
    Stage stage;

    @FXML
    private WebView webviewpaypal;
    private WebEngine engine;
   /* @FXML
    private Label factureici;*/
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		
		engine = webviewpaypal.getEngine();
		loadPage();
	}
    
     public void Exit(ActionEvent event) {
         stage = (Stage) anchor.getScene().getWindow();
         stage.close();
    }
    public void loadPage() {
		engine.load("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=8URSMLRLKR2TC");
	}
}
