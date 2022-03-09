package SalleCinema;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import evenements.*;
public class Salle_Cinema_FXMLController {

    @FXML
    private Button Retour;

    @FXML
    private Button conf;

    @FXML
    private Button ann;

    @FXML
    void Annuler(ActionEvent event) throws IOException {
 Parent root=FXMLLoader.load(getClass().getResource("../evenements/afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @FXML
    void Conferm(ActionEvent event) throws IOException {
 Parent root=FXMLLoader.load(getClass().getResource("SessionSalleCinema.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
 Parent root=FXMLLoader.load(getClass().getResource("../evenements/afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

}

