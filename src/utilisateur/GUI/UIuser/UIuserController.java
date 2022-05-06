/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIuser;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
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
import org.mindrot.jbcrypt.BCrypt;
import utilisateur.entities.interets;
import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;
import static utils.SessionUser.getFs;
import static utils.SessionUser.getUser;
import static utils.SessionUser.setUser;
import utils.md5;


/**
 * FXML Controller class
 *
 * @author malek
 */
// DECLARATION DES VARIABLES 
public class UIuserController implements Initializable {
Parent UI_user;

Map config = ObjectUtils.asMap(
  "cloud_name", "duqo08ysi",
  "api_key", "655598492747666",
  "api_secret", "me7yEUfSm7UEee2jWarnGaBhnY4");
Cloudinary cloudinary = new Cloudinary(config);
    @FXML
    private VBox root;
    @FXML
    private Button quitbutton;
    private JFXButton uploadphoto;
    @FXML
    private ImageView closewindow;
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
    private Rating user_rating;
    @FXML
    private JFXButton cancel_update;
    @FXML
    private AnchorPane upload_window;
    @FXML
    private Label uploadresp;

    private double xOffset = 0; 
    private double yOffset = 0;
    @FXML
    private JFXButton upload;
    @FXML
    private ImageView close_upload;
    @FXML
    private JFXButton publish;
    @FXML
    private JFXButton createevent;
    @FXML
    private ImageView photoup;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton add_interet;
    List<interets> interet;
    @FXML
    private Label label_interet;
    @FXML
    private AnchorPane gesteven;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // Configuration de l'API CLODUINARY POUR L'AVATAR DE L'UTILISATEUR
            Map config = ObjectUtils.asMap(
            "cloud_name", "duqo08ysi",
            "api_key", "655598492747666",
            "api_secret", "me7yEUfSm7UEee2jWarnGaBhnY4");
            Cloudinary cloudinary = new Cloudinary(config);
        user_rating.setRating(getUser().getEvaluation());
        List<interets> interet = getFs().getAllInterets(getUser().getID_Utilisateur());
        System.out.println("THIS IS LIST"+interet);
        ObservableList<interets> My_interet = FXCollections.observableArrayList(interet);
        System.out.println(My_interet);
        for (interets mylist : My_interet) {
        interet_cell.setCellValueFactory(new PropertyValueFactory<interets,String>("nom_interet")); 
        user_interets.setItems(My_interet);
        }
        interets.getItems().addAll(getFs().getAllInterets_Combobox());
        interets.getCheckModel().check(interets.getItems().get(0));
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
    ProfilePicture();
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
        String Updated_pwd=password.getText();
//            System.out.println("MOT DE PASSE SAISIE"+md5.getMd5(Update_pwd));
//            System.out.println("DB PASSWORD"+getUser().getMdp());
//            System.out.println(md5.getMd5(Update_pwd).equals(getUser().getMdp()));
 if (!Update_email.isEmpty() && !Update_phone.isEmpty() && !Update_genre.isEmpty() && !Updated_pwd.isEmpty()){
             if (Update_pwd.isEmpty()){
            modification.setText("Veuillez Confirmer la modification par le saisie de votre mot de passe");
        }else {
            if (BCrypt.hashpw(getUser().getMdp(), BCrypt.gensalt(13)).equals(getUser().getMdp())){
            utilisateur updateduser= new utilisateur(dateb,Update_phone,Update_email,Updated_pwd,Update_genre);
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
            modification.setText("* Modification effectué !");
            modification.setTextFill(Color.GREEN);

        } else {
            modification.setText("* Verifiez vos informations !");
        }
            } else {
            modification.setText("* Verifier votre mot de passe !");

            }
        }
 }else {
     modification.setText("* Veuillez vérifier les champs !");
 }


     }
     public void CancelUpdate(ActionEvent e){
              
          }
          
     private void ProfilePicture(){
         if (getFs().PictureCheck(getUser().getID_Utilisateur())){
               default_avatar.setVisible(false);   
         Image profilepicture = null;
             try{
        URL url = new URL(getUser().getAvatar());
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
           profilepicture = new Image(inputStream);
           defaultpic.setFill(new ImagePattern(profilepicture));
           getUser();
    }catch (IOException e){
        e.printStackTrace();
    }
         }
     }


    @FXML
    private void click(MouseEvent event) throws IOException {
             upload_window.setVisible(true);
    }
    // LES NAVIGATION PAR MENU : 
    @FXML
     private void profileTAB(MouseEvent event){
         // Styling du menu
          profile.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
          collab.setStyle("-fx-background-color: #008080");
          gesteven.setStyle("-fx-background-color: #008080");
          pub.setStyle("-fx-background-color: #008080");
          collab.getStyleClass().add("ui-menu");
          pub.getStyleClass().add("ui-menu");
          gesteven.getStyleClass().add("ui-menu");

     }
     @FXML
      private void CollabTAB(MouseEvent event){
           // Styling du menu
          profile.getStyleClass().add("ui-menu");
          gesteven.getStyleClass().add("ui-menu");
           profile.setStyle("-fx-background-color: #008080");
          collab.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
          pub.getStyleClass().add("ui-menu");
           pub.setStyle("-fx-background-color: #008080");
           gesteven.setStyle("-fx-background-color: #008080");
          // Ouvrir la fenetre du collab
          
          
         try {       
               UI_user = FXMLLoader.load(getClass().getResource("../../../collab/gui/Collabw.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIuserController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(UI_user);   
            Stage UI_stage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
          
              UI_user.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
                  UI_user.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               UI_stage.setX(event.getScreenX() - xOffset);
             UI_stage.setY(event.getScreenY() - yOffset);
            }
        });
            UI_stage.hide();
            UI_stage.setScene(scene);
            UI_stage.show();
      }
      @FXML
       private void offresTAB(MouseEvent event){
            // Styling du menu
          profile.getStyleClass().add("ui-menu");
          collab.getStyleClass().add("ui-menu");
          gesteven.getStyleClass().add("ui-menu");
          profile.setStyle("-fx-background-color: #008080");
          collab.setStyle("-fx-background-color: #008080");
         gesteven.setStyle("-fx-background-color: #008080");
          pub.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
       }
       @FXML
     private void EventTAB(MouseEvent event){
         // Styling du menu
          profile.setStyle("-fx-background-color: #008080");
          collab.setStyle("-fx-background-color: #008080");
          pub.setStyle("-fx-background-color: #008080");
          gesteven.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
                  
          profile.getStyleClass().add("ui-menu");
          collab.getStyleClass().add("ui-menu");
          pub.getStyleClass().add("ui-menu");
            try {       
               UI_user = FXMLLoader.load(getClass().getResource("../../../evenements/afficherEvenement.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIuserController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(UI_user);   
            Stage UI_stage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
          
              UI_user.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
                  UI_user.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               UI_stage.setX(event.getScreenX() - xOffset);
             UI_stage.setY(event.getScreenY() - yOffset);
            }
        });
            UI_stage.hide();
            UI_stage.setScene(scene);
            UI_stage.show();
     }
         public void AnnulerUpdate(ActionEvent e){
        email.setText(getUser().getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(getUser().getDatenaissance(),formatter);
        dob.setValue(localDate);
        phone.setText(getUser().getNum_tel());
        genre.setValue(getUser().getGenre());
        pwd.setText("");
          }

@FXML
    private void UploadImage(ActionEvent event) throws IOException {
    FileChooser fc = new FileChooser();
    File selectedImage = fc.showOpenDialog(null);
    if(selectedImage == null){
    uploadresp.setText("* Veuillez Choisir une image !"); 
    uploadresp.setTextFill(Color.RED);
    } else {
        try {
            Map uploadResult = cloudinary.uploader().upload(selectedImage, ObjectUtils.emptyMap());
            System.out.println(uploadResult);
            String link = (String) uploadResult.get("secure_url"); 
            getFs().PictureUpload(getUser().getID_Utilisateur(),link);
            setUser(getFs().UserById(getUser().getID_Utilisateur()));
            System.out.println("PHOTO LINK IS "+link);
            uploadresp.setText("* Image bien téléchargée !"); 
            uploadresp.setTextFill(Color.GREEN);
            ProfilePicture();
        } catch (Exception e) {
             uploadresp.setText("* Un problème survenu lors de l'upload !"); 
            uploadresp.setTextFill(Color.RED);
            System.out.println("UPLOAD FAILED"+e);
        }
    
    }
    }
    @FXML
             public void CloseUploadWindow(MouseEvent e){
             upload_window.setVisible(false);
          }
         @FXML
        private void Deconnexion(ActionEvent e) throws IOException, InterruptedException {
        setUser(null);
         try {       
               UI_user = FXMLLoader.load(getClass().getResource("../auth/auth.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIuserController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void Ajoutint_Supprint(ActionEvent event) {
        UtilisateurService userv= new UtilisateurService();
        System.out.println(interets.getCheckModel().getCheckedItems());
        System.out.println("HELLO MDF");
         ObservableList<String> interetChecked = interets.getCheckModel().getCheckedItems();
         System.out.println(interetChecked);
         for (String interet : interetChecked) {
         userv.ajouter_interet(getUser().getID_Utilisateur(), interet);
         System.out.println(interet);
         List<interets> myinteretList = getFs().getAllInterets(getUser().getID_Utilisateur());
        ObservableList<interets> My_interet = FXCollections.observableArrayList(myinteretList);
        interet_cell.setCellValueFactory(new PropertyValueFactory<interets,String>("nom_interet")); 
        user_interets.setItems(My_interet);
}
       }

    @FXML
    private void Publication(MouseEvent event) {
         try {       
               UI_user = FXMLLoader.load(getClass().getResource("../../../publication/gui/PublicationInterface.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIuserController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(UI_user,1080,720);   
            Stage UI_stage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
          
              UI_user.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
                  UI_user.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               UI_stage.setX(event.getScreenX() - xOffset);
             UI_stage.setY(event.getScreenY() - yOffset);
            }
        });
            UI_stage.hide();
            UI_stage.setScene(scene);
            UI_stage.show();
    }


    
    
    
}