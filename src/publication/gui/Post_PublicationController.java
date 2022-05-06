/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;
import publication.entities.publication;
import utilisateur.entities.utilisateur;
import publication.services.publicationService;
import static utils.SessionUser.getUser;

public  class Post_PublicationController implements Initializable{
    @FXML
    private Button btn_pub;
    @FXML
    private ImageView user_image;
    @FXML
    private Label username_pub;
    @FXML
    private TextArea description_pub;
    @FXML
    private Button btn_img;
    @FXML
    private Button btn_emoji;
    @FXML
    private Button btn_img1;
    @FXML
    public MenuButton gif_menubtn;
    String IMG_URL="";
    @FXML
    public MenuItem menu_gifitem;
        @FXML
    private AnchorPane Post_anchor;

    @FXML
    private MenuButton img_btn;

    @FXML
    private MenuButton emoji_menubtn;
    @FXML
    private Label counter;
    
        private MediaView mmmv;
    
    @FXML
    private MenuItem emoji_menuitem;
    public void img_btn()
    {
        
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new  FileChooser.ExtensionFilter("Fichier image", "*.jpg","*.jpeg","*.png","*.gif"));
        File f=fc.showOpenDialog(null);
        if (f!=null)
        {
            System.out.println("File Path : "+f.getAbsolutePath().toString());
            IMG_URL="file:"+f.getAbsolutePath().toString();
            Image imageee = new Image("file:"+f.getAbsolutePath());
            ImageView myimg=new ImageView();
             myimg.setImage(imageee);
           myimg.setFitHeight(450);
           myimg.setFitWidth(350);
           myimg.setPreserveRatio(true);
           myimg.setSmooth(true);
           myimg.setCache(true);
           myimg.setLayoutX(50);
           myimg.setLayoutY(207);
           Post_anchor.getChildren().add(myimg);
        }
    }
    
    @FXML
    public void gif_menubtn_act(ActionEvent e)
    {

        System.out.println("Clicked GIF");
     //   OnPressedKey("deadpool");
   

            try {
            FXMLLoader fxmlLoaderPostPub=new FXMLLoader();
            fxmlLoaderPostPub.setLocation(getClass().getResource("Giphy.fxml"));
            AnchorPane anchorPostPub = fxmlLoaderPostPub.load();
            GiphyController PostPubController =fxmlLoaderPostPub.getController();
            PostPubController.OnPressedKey("NOKEYWORDENTERED");
            menu_gifitem.setGraphic(anchorPostPub);
            } catch (IOException ex) {
                Logger.getLogger(Post_PublicationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            Logger.getLogger(Post_PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
               //       MenuItem MT=new MenuItem();
               //       gif_menubtn.getItems().add(MT);
               //        MT.setGraphic(anchorPostPub);
               //gif_menubtn.getItems().set(0, menu_gifitem)
    //}
 }
    @FXML
    public void emoji_menubtn()
    {

        System.out.println("Clicked EMOJI");
            try {
            FXMLLoader fxmlLoaderPostEmoji=new FXMLLoader();
            fxmlLoaderPostEmoji.setLocation(getClass().getResource("Emoji.fxml"));
            AnchorPane anchorPostPub = fxmlLoaderPostEmoji.load();
            EmojiController PostPubControllerEmoji =fxmlLoaderPostEmoji.getController();
            PostPubControllerEmoji.call_me_emoji("");
            emoji_menuitem.setGraphic(anchorPostPub);
            } catch (IOException ex) {
                Logger.getLogger(Post_PublicationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            Logger.getLogger(Post_PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void CheckForm()
    {
       // System.out.println("HELLO ");
        int i=description_pub.getText().length()+1;
        counter.setText(i+"/255");
        System.out.println(i<255);
        if ((i>-1) && (i<255))
            btn_pub.setDisable(false);
        else
            btn_pub.setDisable(true);
    }
    @FXML
       public void Publier()
   {
    String str=description_pub.getText();
    Anti_BadWords AN=new Anti_BadWords();
    String Cleaned=AN.TextAnalyzer(str);
    //   System.out.println("TXT : "+Cleaned);
    //USER SESSION
    utilisateur USession = new utilisateur();
    USession = getUser();
    //USS
     publication P=new publication(Cleaned,IMG_URL);
     publicationService PS=new publicationService();
     PS.Creer_P(P,USession);
   }
       
       public void SetData(utilisateur U)
       {
           System.out.println("Hello");
      username_pub.setText(U.getNom()+" "+U.getPrenom());     
       }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized");
        
    }
}
