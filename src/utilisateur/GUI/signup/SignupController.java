/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.signup;

import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */ 
public class SignupController implements Initializable{
    ObservableList list = FXCollections.observableArrayList();
@FXML
private ComboBox<String> signup_genre;
private Button signup_cancel;

    /**
     * Initializes the controller class.
     */
    public void cancel(ActionEvent e){
        Stage stage = (Stage) signup_cancel.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){

      signup_genre.getItems().addAll(
    "Homme",
    "Femme",
    "Autre"
);
}

}
