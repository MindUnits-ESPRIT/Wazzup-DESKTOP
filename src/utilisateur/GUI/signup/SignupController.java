/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.signup;

import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.xml.validation.Validator;
import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author malek
 */ 
public class SignupController implements Initializable{
@FXML
private ComboBox<String> signup_genre;
@FXML
private JFXButton signup_cancel;
@FXML
private JFXButton signup;
@FXML
private JFXTextField signup_nom;
@FXML
private JFXTextField signup_prenom;
@FXML
private JFXDatePicker signup_dateb;
@FXML
private JFXTextField signup_phone;
@FXML
private JFXTextField signup_email;
@FXML
private JFXPasswordField signup_pwd;
@FXML
private JFXPasswordField signup_pwd1;
@FXML
private JFXProgressBar progressbar;
@FXML
private Label signup_verif;
@FXML
private Label invalid_email;
@FXML
private Label invalid_pwd1;
@FXML
private ImageView closewindow;
    @FXML
    private Label invalid_phone;
public boolean validnom=false;
public boolean validprenom=false;
public boolean validdateb=false;
public boolean validphone=false;
public boolean validemail=false;
public boolean validpassword=false;
public boolean validpasswordverif=false;
Parent Token;
    @FXML
    private ImageView loading;
    @FXML
    private AnchorPane main;
    @FXML
    private Label invalid_pwd;
    /**
     * Initializes the controller class.
     */
    public void closeWindow(MouseEvent e){
    Stage stage = (Stage) closewindow.getScene().getWindow();
    stage.close();
     }
    @FXML
    public void cancel(ActionEvent e){
try {       
               Token = FXMLLoader.load(getClass().getResource("../auth/auth.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(Token);      
            Stage return_Stage = (Stage) (((Node) e.getSource()) .getScene().getWindow());
            return_Stage.hide();
            return_Stage.setScene(scene);
            return_Stage.show();
    }
    public boolean verifications(){
        String nom=signup_nom.getText();
         String prenom=signup_prenom.getText();
         String email=signup_email.getText();
         String telephone=signup_phone.getText();
         String password=signup_pwd.getText();
         String passwordverif=signup_pwd1.getText();
         String genre=signup_genre.getValue();
         
           if (password.equals(passwordverif))     
        {
            return true;
    } else signup_verif.setText("* Les mot de passes ne sont pas compatibles !"); return false; 
}
    public void registration(){
        UtilisateurService userv= new UtilisateurService();
        String nom=signup_nom.getText();
         String prenom=signup_prenom.getText();
         LocalDate dateb=signup_dateb.getValue();
         String birthdate = dateb.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         String email=signup_email.getText();
         String telephone=signup_phone.getText();
         String password=signup_pwd.getText();
         String passwordverif=signup_pwd1.getText();
         String genre=signup_genre.getValue();
         System.out.println(dateb);
         System.out.println(genre);
         System.out.println(nom.length());
     if (verifications()){
            utilisateur newuser = new utilisateur(nom,prenom,birthdate,telephone,genre,email,password,"User");
            userv.ajouter(newuser);
            System.out.println(userv.added);
            // Si le compte est crée , une notification s'affichera
            if (userv.added){
           Image img=new Image("file:./src/utilisateur/GUI/resources/checked_24px.png");
            Notifications notificationBuilder=Notifications.create()
                    .title("Succés")
                    .text("Votre compte a été creé")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event){
                            System.out.println("Clicked on notifications");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            }
            
        }else signup_verif.setText("* Verifiez vos informations !");
         
    }
      UtilisateurService userv= new UtilisateurService(); // POUR LES VERIFICATION PAR RAPPORT A LA DB
                                                    // VERIFICATIONS //
  // Email Validation
 public boolean emailValid(String email){
int count=0;
char a='@';  
int atposition = 0,dotposition = 0,flag = 0;
for(int i = 0; i < email.length(); i++) {
    if(email.charAt(i) == a) {
                count++;
                atposition = i;
                if(count >= 2) {
                    flag = 1;
                    break;
                }
           }
        if (email.charAt(i) == '.') {
                dotposition = i;
         }
      }
     if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length() || flag == 1) {
     return false;
      } else 
          return true;
 }
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb){
       signup.setDisable(true);

        // COMBOBOX OPTIONS
     signup_genre.getItems().addAll(
    "Male",
    "Female",
    "Autre"
);
     // VALIDATORS

 
  RequiredFieldValidator validator = new RequiredFieldValidator();

// Verification du permier champs : Nom
signup_nom.getValidators().add(validator);
 validator.setMessage("Veuillez compléter ce champ");
signup_nom.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
    if (!newValue) {
        if(signup_nom.validate()) {signup_nom.setDisable(false);validnom=true;}
        else validnom=false;
      } 

});
  // Verification du champ : Prenom
signup_prenom.getValidators().add(validator);
 validator.setMessage("Veuillez compléter ce champ");
signup_prenom.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
   if (!newValue) {
        if(signup_prenom.validate()) {
            validprenom=true;
            signup_prenom.setDisable(false);
        } else validprenom=false;

      }
});
  // Verification du champ : Date de naissance
signup_dateb.getValidators().add(validator);
 validator.setMessage("Veuillez compléter ce champ");
signup_dateb.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
    if (!newValue) {
        if(signup_dateb.validate()) {signup_dateb.setDisable(false);validdateb=true;}
        else validdateb=false;

      }
});
  // Verification du champ : telephone
signup_phone.getValidators().add(validator);
 validator.setMessage("Veuillez compléter ce champ");
signup_phone.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

    if (!newValue) {
        // Pattern du telephone pour la validation 
            Pattern pattern = Pattern.compile("^\\+\\d{11}$");
            Matcher matcher = pattern.matcher(signup_phone.getText());
        ///
                    System.out.println(matcher.matches());
        if(signup_phone.getText().length()==0) {
            signup_email.setFocusColor(Color.RED);
            invalid_phone.setText(" Veuillez compléter ce champ");
            validphone=false;
//            && (signup_phone.getText().length() != 8 || signup_phone.getText(). length() != 11)

        }else if (matcher.matches()==false){  
            signup_email.setFocusColor(Color.RED);
            invalid_phone.setText(" Votre telephone est invalide");
            validphone=false;
        } else {
            signup_phone.setDisable(false);
            validphone=true;           
            invalid_phone.setText(" ");

            
        }

      }
});
  // Verification VALID+ PAS EXISTANT : Email

//signup_email.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//  String email=signup_email.getText();
//    if (!newValue) {
//        if(signup_email.getText().length()==0) {
//            signup_email.setFocusColor(Color.RED);
//            invalid_email.setText(" Veuillez compléter ce champ");
//            validemail=false;
//        }else if (emailValid(signup_email.getText())==false){  
//            signup_email.setFocusColor(Color.RED);
//            invalid_email.setText(" Votre email est invalide");
//            validemail=false;
//        }else if (userv.isEmailExist(email)==true){  
//            signup_email.setFocusColor(Color.RED);
//            invalid_email.setText(" Votre email existe déja ! ");
//            validemail=false;
//        } else {
//            signup_email.setFocusColor(Color.GREEN);
//            invalid_email.setText("");
//            validemail=true;
//        }
//      }
//});
 // Verification du champ : Mot de passe
//signup_pwd.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//    if (!newValue) {
//        if(signup_pwd.getText().length()==0)
//        {
//           signup_pwd.setFocusColor(Color.RED);
//           invalid_pwd.setText(" Veuillez compléter ce champ");
//           validpassword=false;
//        }else if (userv.checkPassword(signup_pwd.getText())==false){
//           signup_pwd.setFocusColor(Color.RED);
//           invalid_pwd.setText("Il faut choisir un mot de passe plus fort"); 
//           validpassword=false;
//          }else {
//            signup_pwd.setFocusColor(Color.GREEN);
//            invalid_pwd.setText("");
//            validpassword=true;
//        }
//    }
//});

}
@FXML
        public void signup(ActionEvent e){               
      progressbar.setVisible(true);
          
        registration();
                  loading.setVisible(true);
                  main.setOpacity(0.56);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
           try {       
               Token = FXMLLoader.load(getClass().getResource("../Vtoken/Vtoken.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(Token);      
            Stage token_Stage = (Stage) (((Node) e.getSource()) .getScene().getWindow());
            token_Stage.setUserData(signup_email.getText());
            token_Stage.hide();
            token_Stage.setScene(scene);
            token_Stage.show();
    }));
    timeline.play();
        System.out.println("ALL FINE");
    }
        
       public void PhoneVerif(MouseEvent event){
         // Pattern du telephone pour la validation 
            Pattern pattern = Pattern.compile("^\\+\\d{11}$");
            Matcher matcher = pattern.matcher(signup_phone.getText());
        ///
           System.out.println(matcher.matches());
        if(signup_phone.getText().length()==0) {
            signup_email.setFocusColor(Color.RED);
            invalid_phone.setText(" Veuillez compléter ce champ");
            validphone=false;
//            && (signup_phone.getText().length() != 8 || signup_phone.getText(). length() != 11)

        }else if (matcher.matches()==false){  
            signup_email.setFocusColor(Color.RED);
            invalid_phone.setText(" Votre telephone est invalide");
            validphone=false;
        } else {
            signup_phone.setDisable(false);
            validphone=true;           
            invalid_phone.setText(" ");   
        }
       }
       @FXML
       public void EmailVerif(MouseEvent event) {
           String email=signup_email.getText();
        if(signup_email.getText().length()==0) {
            signup_email.setFocusColor(Color.RED);
            invalid_email.setText(" Veuillez compléter ce champ");
            validemail=false;
        }else if (emailValid(signup_email.getText())==false){  
            signup_email.setFocusColor(Color.RED);
            invalid_email.setText(" Votre email est invalide");
            validemail=false;
        }else if (userv.isEmailExist(email)==true){  
            signup_email.setFocusColor(Color.RED);
            invalid_email.setText(" Votre email existe déja ! ");
            validemail=false;
        } else {
            signup_email.setFocusColor(Color.GREEN);
            invalid_email.setText("");
            validemail=true;
        }
       }
        
@FXML
       public void verifPassword(MouseEvent event) {
       if(signup_pwd.getText().length()==0)
        {
           signup_pwd.setFocusColor(Color.RED);
           invalid_pwd.setText(" Veuillez compléter ce champ");
           validpassword=false;
        }else if (userv.checkPassword(signup_pwd.getText())==false){
           signup_pwd.setFocusColor(Color.RED);
           invalid_pwd.setText("Il faut choisir un mot de passe plus fort"); 
           validpassword=false;
          }else {
            signup_pwd.setFocusColor(Color.GREEN);
            invalid_pwd.setText("");
            validpassword=true;
        }
       }
        
@FXML
       public void verifPassword2(MouseEvent event) {
            if(signup_pwd1.getText().length()==0) {
            signup_pwd1.setFocusColor(Color.RED);
            invalid_pwd1.setText("Veuillez confirmer votre mot de passe");
            validpasswordverif=false;
        } else if (!signup_pwd1.getText().equals(signup_pwd.getText())){
            signup_pwd1.setFocusColor(Color.RED);
            invalid_pwd1.setText("La verification n'est pas conforme");
            validpasswordverif=false;
        }else{
            signup_pwd1.setFocusColor(Color.GREEN);
            invalid_pwd1.setText("");
            validpasswordverif=true;
        }
        }
@FXML
        public void verify(MouseEvent me){
        if ((validnom==true) && (validprenom==true) && (validdateb==true) && (validphone==true) && (validemail==true) && (validpassword==true) && (validpasswordverif==true)){
        signup.setDisable(false);
        }  else signup.setDisable(true);

        }

}
