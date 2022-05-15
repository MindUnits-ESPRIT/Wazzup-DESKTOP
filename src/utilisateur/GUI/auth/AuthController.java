/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.auth;
import collab.gui.CollabwController;
import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import org.controlsfx.control.MaskerPane;
import org.mindrot.jbcrypt.BCrypt;
import static utils.SessionUser.*;
import utils.otpsend;
import static utils.otpsend.sendSms;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class AuthController {
    Random rand = new Random();
    int otp_timer=20;
    int otpchecker=0;
    int OTP;
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
    @FXML
    private JFXTextField otp;
    @FXML
    private Label otplabel;
    @FXML
    private JFXButton checkotp;
    @FXML
    private ImageView otptimer;
    @FXML
    private ImageView smsicon1;
    @FXML
    private ImageView smsicon2;
    @FXML
    private Hyperlink forgot;
    @FXML
    private Label forgotpwd;

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
    public void CheckOTP(ActionEvent e){
                 if (Integer.valueOf(otp.getText())==OTP){
                     otptimer.setVisible(false);
                     otptimer.setDisable(true);
                     otpchecker=1;
                     otplabel.setText("Code Verifié ! , Redirection ... ");
                     otplabel.setTextFill(Color.GREEN);
              try {       
               signup = FXMLLoader.load(getClass().getResource("../UIAdmin/UIAdmin.fxml"));
            } catch (IOException ex) {
//                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex + "ERROR");
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
                  } else{
                     otplabel.setText("Veuillez verifier votre code !");
                     otplabel.setTextFill(Color.RED);
                    }
    }
    public void GenOTP(){
        File file = new File("src/utilisateur/GUI/resources/OTPtimer.gif");
        Image image = new Image(file.toURI().toString());
        otptimer.setImage(image);
        
        
        otptimer.setVisible(true);
       otptimer.setDisable(false);
              OTP=rand.nextInt(99999);
              System.out.println(OTP);
              new java.util.Timer().schedule(
        new java.util.TimerTask() {
            @Override
            public void run() {
             Timer timer = new Timer();
            timer. schedule(new TimerTask() {
                @Override
            public void run() {
              OTP=rand.nextInt(99999);
              System.out.println(OTP);
             
}
}, 0, 20000);
          

          
}},20000);
}
    @FXML
        public void LoginButton(ActionEvent e){
          UtilisateurService userv= new UtilisateurService();
             String input_email=auth_email.getText();
            String input_password=auth_password.getText();
            if (input_email.isEmpty()==false && input_password.isEmpty()==false){
              String userpwd=userv.UserByEmail(input_email).getMdp().replace("$2y", "$2a");
               System.out.println(userpwd);
            System.out.println("input_password= "+ BCrypt.checkpw(input_password,userpwd));
            
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
                System.out.println(getUser().getType_user().equals("User"));
              if (getUser().getType_user().equals("User")){
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
              } else {
                  GenOTP();
                  System.out.println("otp is "+OTP);
                  otp.setVisible(true);
                  otptimer.setVisible(true);
                  otplabel.setVisible(true);
                  smsicon1.setVisible(true);
                  smsicon2.setVisible(true);
                  checkotp.setVisible(true);
                  sendSms("Votre Code OTP est : "+OTP);
                  sendSms("Votre Code OTP est : "+OTP,userv.UserByEmail(input_email).getNum_tel());
                  otplabel.setText("Votre code sms a été bien envoyé");
              }
              } else if (result==2){
              auth_verif.setText("* Votre compte n'est pas encore activé ! !"); 
              auth_verif.setTextFill(Color.RED);
              }
    } else {
              auth_verif.setText("* Veuillez remplir les champs necessaires !"); 
              auth_verif.setTextFill(Color.RED);
            }
            }

    @FXML
    private void GeneratePassword(MouseEvent event) throws MessagingException {
      UtilisateurService userv= new UtilisateurService();
        String email=auth_email.getText();
        if (userv.isEmailExist(email)){
            int id=userv.UserByEmail(email).getID_Utilisateur();
            userv.modifierPassword(id,email);
           forgotpwd.setText("Un mot de passe généré a été bien envoyé a votre email ! ");
           forgotpwd.setTextFill(Color.GREEN);
       } else{
           forgotpwd.setText("Veuillez verifier votre email !");
           forgotpwd.setTextFill(Color.RED);
       }
        }
        
}
