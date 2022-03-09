/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIAdmin;
import utilisateur.GUI.UIuser.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.*;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import utilisateur.entities.interets;
import static utils.SessionUser.getUser;
import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;
import static utils.SessionUser.getFs;
import static utils.SessionUser.getUser;
import static utils.SessionUser.setUser;


/**
 * FXML Controller class
 *
 * @author malek
 */
// DECLARATION DES VARIABLES 
public class UIAdminController implements Initializable {
Parent UI_user;

Map config = ObjectUtils.asMap(
  "cloud_name", "duqo08ysi",
  "api_key", "655598492747666",
  "api_secret", "me7yEUfSm7UEee2jWarnGaBhnY4");
    @FXML
    private VBox root;
    @FXML
    private Button quitbutton;
    @FXML
    private ImageView closewindow;
    @FXML
    private ImageView photoup;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label age;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private ComboBox<String> genre;
    @FXML
    private JFXPasswordField pwd;
    @FXML
    private Label modification;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private Rectangle defaultpic;
    @FXML
    private ImageView default_avatar;
    @FXML
    private CheckComboBox<String> interets;
    @FXML
    private TableView<interets> user_interets;
    @FXML
    private TableColumn<interets, String> interet_cell;
    @FXML
    private AnchorPane profile;
    @FXML
    private AnchorPane pub;
    @FXML
    private AnchorPane collab;
    @FXML
    private JFXButton cancel_update;
    @FXML
    private ImageView close_upload;

    private double xOffset = 0; 
    private double yOffset = 0;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


//        Timer timer = new Timer();
//        timer. schedule(new TimerTask() {
//                @Override
//            public void run() {
//              Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//              ProfilePicture();
//            }});
//
//}
//}, 50, 500);
    defaultpic.setArcWidth(30.0);   // Corner radius
    defaultpic.setArcHeight(30.0);

    genre.getItems().addAll(
    "Male",
    "Female",
    "Autre"
);
        
        //Initialisation de la session UTILISATEUR
       utilisateur user = new utilisateur();
       user = getUser();
     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
        
         UtilisateurService us = getFs();
        System.out.println(getUser());
        System.out.println(getFs());
     }));
    timeline.play();
// Les champs de modifications
    prenom.setText(user.getPrenom());
    prenom.setTextFill(Color.WHITE);
    nom.setText(user.getNom());
    nom.setTextFill(Color.WHITE);
    email.setText(user.getEmail());
    phone.setText(user.getNum_tel());
    genre.setValue(user.getGenre());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(user.getDatenaissance(),formatter);
    dob.setValue(localDate);
    }    
  
    
    
    @FXML
     public void closeWindow(MouseEvent e){
        Stage stage = (Stage) closewindow.getScene().getWindow();
        stage.close();
     }
    @FXML
     public void Update(ActionEvent e){
        UtilisateurService userv= new UtilisateurService();
        String Update_email=email.getText();
        String Update_phone=phone.getText();
        LocalDate Update_dob=dob.getValue();
        String dateb = Update_dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String Update_genre=genre.getValue();
        String Update_pwd=pwd.getText();
        utilisateur updateduser= new utilisateur(dateb,Update_phone,Update_email,Update_pwd,Update_genre);
        userv.modifier(getUser().getID_Utilisateur(),updateduser, 2);
        if (userv.modified){
            Image img=new Image("file:./src/utilisateur/GUI/resources/checked_24px.png");
            Notifications notificationBuilder=Notifications.create()
                    .title("Succés")
                    .text("Votre compte a été bien modifié")
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
        } else {
            modification.setText("* Verifiez vos informations !");
        }
     }
    @FXML
     public void CancelUpdate(ActionEvent e){
              
          }
    // LES NAVIGATION PAR MENU : 
    @FXML
     private void profileTAB(MouseEvent event){
         // Styling du menu
          profile.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
          collab.setStyle("-fx-background-color: #008080");
          pub.setStyle("-fx-background-color: #008080");
          collab.getStyleClass().add("ui-menu");
          pub.getStyleClass().add("ui-menu");
     }
     @FXML
      private void CollabTAB(MouseEvent event){
           // Styling du menu
          profile.getStyleClass().add("ui-menu");
           profile.setStyle("-fx-background-color: #008080");
          collab.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
          pub.getStyleClass().add("ui-menu");
           pub.setStyle("-fx-background-color: #008080");
      }
      @FXML
       private void usersTAB(MouseEvent event){
            // Styling du menu
          profile.getStyleClass().add("ui-menu");
          collab.getStyleClass().add("ui-menu");
          profile.setStyle("-fx-background-color: #008080");
          collab.setStyle("-fx-background-color: #008080");
          pub.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
       }
         public void AnnulerUpdate(ActionEvent e){
        email.setText(getUser().getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(getUser().getDatenaissance(),formatter);
        dob.setValue(localDate);
        phone.setText(getUser().getNum_tel());
        genre.setValue(getUser().getGenre());
          }
         @FXML
        private void Deconnexion(ActionEvent e) throws IOException, InterruptedException {
        setUser(null);
         try {       
               UI_user = FXMLLoader.load(getClass().getResource("../auth/auth.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(UI_user);   
             scene.setFill(Color.TRANSPARENT);
            Stage UI_stage = (Stage) (((Node) e.getSource()) .getScene().getWindow());
            
                   UI_user.setOnMousePressed(ev -> {
            xOffset = ev.getSceneX();
            yOffset = ev.getSceneY();
        });
        UI_user.setOnMouseDragged(ev -> {
            UI_stage.setX(ev.getScreenX() - xOffset);
            UI_stage.setY(ev.getScreenY() - yOffset);
        });
            UI_stage.hide();
            UI_stage.setScene(scene);
            UI_stage.show();
        }
    
    
    
}
