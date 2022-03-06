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
import com.jfoenix.controls.JFXToggleButton;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.*;
import static utils.SessionUser.getUser;

import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;
import utils.SessionUser;
import static utils.SessionUser.getFs;

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
    @FXML
    private JFXButton publish;
    @FXML
    private JFXButton createevent;
    private JFXButton uploadphoto;
    @FXML
    private JFXButton closewindow;
    @FXML
    private Circle photoup;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialisation de la session UTILISATEUR
      utilisateur user = new utilisateur();
      user = getUser();
       UtilisateurService us = getFs();
        System.out.println(getUser());

        System.out.println(getFs());
        prenom.setText(user.getPrenom());
        prenom.setTextFill(Color.WHITE);
        nom.setText(user.getNom());
        nom.setTextFill(Color.WHITE);

        
    }    
    @FXML
     public void closeWindow(ActionEvent e){
        Stage stage = (Stage) closewindow.getScene().getWindow();
        stage.close();
     }


    @FXML
    private void click(MouseEvent event) throws IOException {
       Parent popup = FXMLLoader.load(getClass().getResource("Imageupload/Imageupload.fxml"));
       Scene scene= new Scene(popup);
       Stage stage = new Stage();
       stage.setTitle("Upload profile picture");
       stage.setScene(scene);
       stage.show();
    }
}
