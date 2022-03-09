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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Initialisation de la session UTILISATEUR
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(user.getDatenaissance(), formatter);
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
        Stage UI_stage = (Stage) (((Node) e.getSource()).getScene().getWindow());

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

}
