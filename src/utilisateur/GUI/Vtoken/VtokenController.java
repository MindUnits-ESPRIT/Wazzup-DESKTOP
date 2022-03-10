/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.Vtoken;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilisateur.GUI.auth.AuthController;
import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class VtokenController implements Initializable {
 Parent tokenverif;
    @FXML
    private JFXProgressBar progressbar;
    @FXML
    private AnchorPane tokenmain;
    @FXML
    private JFXButton verifier;
    String mailtoken;
    @FXML
    private JFXTextField token;
    @FXML
    private Label tokenreponse;
    @FXML
    private Label validated;
    @FXML
 public void VerifyButton(ActionEvent e){
UtilisateurService userv= new UtilisateurService();
        if (userv.CheckToken(mailtoken, token.getText())){
            userv.ConfirmerCompte(mailtoken);
            tokenreponse.setText("Votre compte a été bien activé");
            tokenreponse.setTextFill(Color.GREEN);
            validated.setText("Un mail de confirmation vous a été envoyé");
            validated.setTextFill(Color.GREEN);
             Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), ev -> {
            // Redirection vers la page d'authentification
           try {       
               tokenverif = FXMLLoader.load(getClass().getResource("../auth/auth.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(tokenverif);      
            Stage Signup_Stage = (Stage) (((Node) e.getSource()) .getScene().getWindow());
            Signup_Stage.hide();
            Signup_Stage.setScene(scene);
            Signup_Stage.show();
         }));
              timeline.play();
        } else {
            tokenreponse.setText("Veuillez verifier votre Token ! ");
            tokenreponse.setTextFill(Color.RED);
            validated.setText("");
        }    
 }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /// La reception de l'addresse mail associé au compte a valider par TOKEN
      new java.util.Timer().schedule(
        new java.util.TimerTask() {
            @Override
            public void run() {
                 Stage tstage = (Stage) tokenmain.getScene().getWindow();
                 mailtoken=tstage.getUserData().toString();
                 System.out.println("MAIL TOKEN IS "+mailtoken);
            }},50);
    }    

    
}
