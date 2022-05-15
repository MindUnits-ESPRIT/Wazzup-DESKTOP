/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rencontre;

import Rencontre.services.RencontreService;
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
public class ModifierRencontreController implements Initializable {

    
    @FXML
    private JFXTextField TypeFiled;

    @FXML
    private Label WrongType;

    @FXML
    void Modify(ActionEvent event) {
        if (checkFields()){
        RencontreService rs = new RencontreService();
        rs.modifier(34, TypeFiled.getText());
        }
        
    }
    public boolean checkFields(){
       boolean wrong = true;
        if (TypeFiled.getText().isEmpty()){
            WrongType.setText("Veuillez ajouter un type");
            wrong=false;
        }
        return wrong;
        
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("AfficheRencontre.fxml"));
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
