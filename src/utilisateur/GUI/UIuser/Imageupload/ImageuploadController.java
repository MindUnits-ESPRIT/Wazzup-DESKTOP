/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.GUI.UIuser.Imageupload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class ImageuploadController implements Initializable {
    
    Map config = ObjectUtils.asMap(
  "cloud_name", "duqo08ysi",
  "api_key", "655598492747666",
  "api_secret", "me7yEUfSm7UEee2jWarnGaBhnY4");
  Cloudinary cloudinary = new Cloudinary(config);
    @FXML
    private JFXButton closewindow;
    @FXML
    private JFXButton upload;
    @FXML
    private Label uploadresp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
    Stage stage = (Stage) closewindow.getScene().getWindow();
    stage.close();
    }
    @FXML
    private void UploadImage(ActionEvent event) throws IOException {
    FileChooser fc = new FileChooser();
    File selectedImage = fc.showOpenDialog(null);
    if(selectedImage == null){
    uploadresp.setText("* Veuillez Choisir une image !"); 
    uploadresp.setTextFill(Color.RED);
    } else {
    uploadresp.setText("* Image bien téléchargée !"); 
    uploadresp.setTextFill(Color.GREEN);
    Map uploadResult = cloudinary.uploader().upload(selectedImage, ObjectUtils.emptyMap());
    System.out.println(uploadResult);
    String link = (String) uploadResult.get("secure_url");   
    System.out.println("PHOTO LINK IS "+link);
    }
    }
    
}
