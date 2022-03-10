package SalleCinema;

import SalleCinema.entities.SalleCinema;
import SalleCinema.services.SalleCinemaService;
import com.jfoenix.controls.JFXTextField;
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
import evenements.URL.URL;
import evenements.entities.evenements;
import java.sql.SQLException;
import javafx.scene.control.Label;
public class Salle_Cinema_FXMLController {

    @FXML
    private Button Retour;

    @FXML
    private Button conf;

    @FXML
    private Button ann;
    
  @FXML
    private JFXTextField NomField;

    @FXML
    private Label wrongNom;
    @FXML
    void Annuler(ActionEvent event) throws IOException {
 Parent root=FXMLLoader.load(getClass().getResource("../evenements/afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

    @FXML
    void Conferm(ActionEvent event) throws IOException, SQLException {
        if(checkFields()){
 Parent root=FXMLLoader.load(getClass().getResource("SessionSalleCinema.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
URL u = new URL();
SalleCinemaService cs = new SalleCinemaService();
SalleCinema c = new SalleCinema(NomField.getText(),u.GetUrl_SalleCinema(5));
evenements e = new evenements(33);
cs.ajouter(c, e);
            System.out.println("Salle Cinema ajoute");
    }}
public boolean checkFields(){
    boolean wrong = true;
    if(NomField.getText().isEmpty()){
        NomField.setText("Veuillez ajouter le nom");
        wrong = false;
    }
    else {
        wrong = true;
    }
        return wrong;
    
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

