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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import utilisateur.entities.interets;
import utilisateur.entities.utilisateur;
import utilisateur.services.UtilisateurService;
import static utils.SessionUser.getFs;
import static utils.SessionUser.getUser;
import static utils.SessionUser.setUser;

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
    @FXML
    private AnchorPane collab;
    @FXML
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(user.getDatenaissance(),formatter);
    dob.setValue(localDate);
    }    

    @FXML
    private void closeWindow(MouseEvent event) {
    }

    @FXML
    private void Update(ActionEvent event) {
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
