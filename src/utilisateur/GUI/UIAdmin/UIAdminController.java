/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIAdmin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
<<<<<<< HEAD
import org.mindrot.jbcrypt.BCrypt;
=======
>>>>>>> 5eb45895ada8a787103e684f6c40c6fbc98833f1
import utilisateur.entities.interets;
import utilisateur.entities.utilisateur;
import utilisateur.services.UtilisateurService;
import static utils.SessionUser.getFs;
import static utils.SessionUser.getUser;
import static utils.SessionUser.setUser;
import utils.md5;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class UIAdminController implements Initializable {
    Parent UI_admin;
    @FXML
    private VBox root;
    @FXML
    private ImageView closewindow;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXButton cancel_update;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXPasswordField pwd;
    @FXML
    private Label modification;
    @FXML
    private AnchorPane profile;
    private AnchorPane collab;
    private AnchorPane pub;
    @FXML
    private Button quitbutton;
    @FXML
    private Label age;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    private double xOffset = 0; 
    private double yOffset = 0;
    @FXML
    private TableView<utilisateur> tab_user;
    @FXML
    private TableColumn<utilisateur, String> mail;
    @FXML
    private TableColumn<utilisateur, Boolean> act;
    @FXML
    private TableColumn<utilisateur, String> eval;
    @FXML
    private TableColumn<utilisateur, Boolean> sponsor;
    @FXML
    private TableColumn<utilisateur, String> id;
    @FXML
    private JFXButton delete_u;
    @FXML
    private Label errarea;
    @FXML
    private AnchorPane modif_compte;
    @FXML
    private AnchorPane gestuser;
    @FXML
    private JFXPasswordField password;
    @FXML
    private ComboBox<String> genre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          UtilisateurService us = new UtilisateurService();
        List<utilisateur> users = us.afficher();
        ObservableList<utilisateur> all_users = FXCollections.observableArrayList(users);
        id.setCellValueFactory(new PropertyValueFactory<utilisateur,String>("ID_Utilisateur")); 
        mail.setCellValueFactory(new PropertyValueFactory<utilisateur,String>("email")); 
        act.setCellValueFactory(new PropertyValueFactory<utilisateur,Boolean>("activated")); 
        eval.setCellValueFactory(new PropertyValueFactory<utilisateur,String>("evaluation")); 
        sponsor.setCellValueFactory(new PropertyValueFactory<utilisateur,Boolean>("sponsor")); 
        
        tab_user.setItems(all_users);

        //Initialisation de la session UTILISATEUR
       utilisateur user = new utilisateur();
       user = getUser();
     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
        
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
    genre.setValue(getUser().getGenre());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(user.getDatenaissance(),formatter);
    dob.setValue(localDate);
    }    

    @FXML
    private void closeWindow(MouseEvent event) {
    }

    @FXML
    private void Update(ActionEvent event) {
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
<<<<<<< HEAD
            if (BCrypt.hashpw(getUser().getMdp(), BCrypt.gensalt(13)).equals(getUser().getMdp())){
=======
            if (md5.getMd5(Update_pwd).equals(getUser().getMdp())){
>>>>>>> 5eb45895ada8a787103e684f6c40c6fbc98833f1
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

    @FXML
    private void Deconnexion(ActionEvent e) {
        setUser(null);
         try {       
               UI_admin = FXMLLoader.load(getClass().getResource("../auth/auth.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(UI_admin);   
             scene.setFill(Color.TRANSPARENT);
            Stage UI_stage = (Stage) (((Node) e.getSource()) .getScene().getWindow());
            
                   UI_admin.setOnMousePressed(ev -> {
            xOffset = ev.getSceneX();
            yOffset = ev.getSceneY();
        });
        UI_admin.setOnMouseDragged(ev -> {
            UI_stage.setX(ev.getScreenX() - xOffset);
            UI_stage.setY(ev.getScreenY() - yOffset);
        });
            UI_stage.hide();
            UI_stage.setScene(scene);
            UI_stage.show();
        }
        @FXML
    private void USERTAB(MouseEvent event){
         // Styling du menu
          gestuser.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
          profile.setStyle("-fx-background-color: #008080");
          tab_user.setVisible(true);
          modif_compte.setVisible(false);

    }
    @FXML
        private void PROFILETAB(MouseEvent event){
         // Styling du menu
          gestuser.setStyle("-fx-background-color: #008080");
          profile.setStyle("-fx-background-color: rgba(31, 217, 184, 1)");
          modif_compte.setVisible(true);
          tab_user.setVisible(false);
    }
    @FXML
    private void supprimer(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        if (tab_user.getSelectionModel().getSelectedItems().isEmpty()){
            errarea.setText("il faut choisir un utilisateur a supprimer"); }
        else {
           int u = tab_user.getSelectionModel().getSelectedItems().get(0).getID_Utilisateur();
            System.out.println(u);
            us.supprimer(u);
         }
    }
    
}
