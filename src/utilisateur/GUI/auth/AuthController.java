/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.auth;
import collab.gui.CollabwController;
import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.controlsfx.control.MaskerPane;
import static utils.SessionUser.*;
import static utils.md5.getMd5;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class AuthController {
    @FXML
Parent signup;
    @FXML
    private JFXTextField auth_email;
    @FXML
    private JFXPasswordField auth_password;
    @FXML
    private Button auth_cancel;
    @FXML
    public Label auth_verif;
    @FXML
    private Button auth_login;
    private double xOffset = 0; 
    private double yOffset = 0;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void cancelButton(ActionEvent e){
        Stage stage = (Stage) auth_cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void Signup(ActionEvent event) {
         try {       
               signup = FXMLLoader.load(getClass().getResource("../signup/signup.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(signup);      
            Stage Signup_Stage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            Signup_Stage.hide();
            Signup_Stage.setScene(scene);
            Signup_Stage.show();
            
    }
    @FXML
        public void LoginButton(ActionEvent e){
          UtilisateurService userv= new UtilisateurService();
             String input_email=auth_email.getText();
            String input_password=auth_password.getText();
            if (input_email.isEmpty()==false && input_password.isEmpty()==false){

            System.out.println("input_password= "+input_password);
            utilisateur auth = new utilisateur(input_email,input_password);
            int result= userv.auth(auth);
               System.out.println(result);
            if (result==0){
              auth_verif.setText("* Verifiez vos informations !");
               auth_verif.setTextFill(Color.RED);
            }
            else if (result==1){
              auth_verif.setText("* Bienvenue");
              auth_verif.setTextFill(Color.GREEN);
              setUser(userv.UserByEmail(input_email));
            try {       
               signup = FXMLLoader.load(getClass().getResource("../UIuser/UIuser.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(signup);   
            Stage UI_stage = (Stage) (((Node) e.getSource()) .getScene().getWindow());
            
                   signup.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        signup .setOnMouseDragged(event -> {
            UI_stage.setX(event.getScreenX() - xOffset);
            UI_stage.setY(event.getScreenY() - yOffset);
        });
            UI_stage.hide();
            UI_stage.setScene(scene);
            UI_stage.show();
            
              } else if (result==2){
              auth_verif.setText("* Votre compte n'est pas encore activé ! !"); 
              auth_verif.setTextFill(Color.RED);
              }
    } else {
              auth_verif.setText("* Veuillez remplir les champs necessaires !"); 
              auth_verif.setTextFill(Color.RED);
            }
            }
        
    
    
}
