/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;

import SalleCinema.services.SalleCinemaService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SRN
 */
public class SupprimerSalleController implements Initializable {

    
    @FXML
    private Label NomVide;
 @FXML
    private JFXTextField Salle;
    @FXML
    void Modify(ActionEvent event) {
if (checkFields()){
    SalleCinemaService ss = new SalleCinemaService();
    ss.modifier(19, Salle.getText(), "Venom");
}

    }
    public boolean checkFields(){
        boolean validModif=false;
if(Salle.getText().isEmpty()){
    NomVide.setText("Veuillez remplir le nom");
  validModif=false;
}
else 
     validModif=true;
return validModif;
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("afficherSalleCinema.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
