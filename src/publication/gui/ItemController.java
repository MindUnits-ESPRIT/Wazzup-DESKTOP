/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

import commentaire.entities.commentaire;
import commentaire.services.commentaireService;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.swing.Spring.width;
import publication.entities.publication;
import utilisateur.entities.utilisateur;
import static utils.SessionUser.getUser;
import paiement.GUI.*;

/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class ItemController {
    private Pattern pattern = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*");
    private String url;
    Parent UI_Payment,root;
    @FXML
    private AnchorPane AnchorPanePub;
    @FXML
    private ImageView user_image;
    @FXML
    private TextArea user_description;
    @FXML
    private GridPane grid_c;
    @FXML
    public MenuButton user_action;
    @FXML
    private Hyperlink user_name;
    @FXML
    private ImageView image_pub;
    @FXML
    private ScrollPane scroll_c;
    @FXML
    private Button jaime_btn;
    @FXML
    private Label pub_date;
    @FXML
    private Button commenter_btn;
    @FXML
    private HBox HBox_Pub;
    @FXML
    private Button partager_btn;
    static public Stage newWindow2=new Stage();
    Image image_p;
    int i;
    @FXML
    private MenuItem spon_btn;
  
        public void setData(publication P,utilisateur U,String str)
        {
            user_action.getItems().get(0).setText(valueOf(P.getID_publication()));
            pub_date.setText("Publi?? "+P.getDate().substring(0,16));
            user_name.setText(U.getNom()+" "+U.getPrenom());
            user_description.setText(P.getDescription());
            //play();
            if (P.getFichier().equalsIgnoreCase("NULL") || P.getFichier().length()==0)
            {
                image_pub.setVisible(false);
                int X=(int) user_description.getTranslateX();
                int Y=(int) user_description.getTranslateY()-150;
                System.out.println(X);
                //jaime_btn.setTranslateX(X-50);
                jaime_btn.setTranslateY(Y+25);
               // commenter_btn.setTranslateX(X-50);
                commenter_btn.setTranslateY(Y+25);
               // partager_btn.setTranslateX(X-30);
                partager_btn.setTranslateY(Y+25);
            }
            else
            {
                image_pub.setVisible(true);
                System.out.println("IMAGE UIRL"+P.getFichier().toString());
                if (P.getFichier().toString().contains("www."))
                    System.out.println("LINK : "+P.getFichier().toString());
              //  image_p=new Image(P.getFichier());
                else
                {System.out.println("127.0.0.1:8000/media/cache/ImgPublicLoad/images/posts/"+P.getFichier().toString());
                image_p=new Image("https://cdn.discordapp.com/attachments/623259868731670571/975382316945920020/unknown.png");    
//                "127.0.0.1:8000/media/cache/ImgPublicLoad/images/posts/"+P.getFichier()
                }
                System.out.println("Image URL :"+P.getFichier().toString());
                Image image_p=new Image(P.getFichier());
                image_pub.setImage(image_p);
                image_pub.getFitHeight();
                image_pub.getFitWidth();
                image_pub.setTranslateX(image_pub.getTranslateX());
            }
               commentaireService CS = new commentaireService();
            List<commentaire> List_Commentaire=new ArrayList<commentaire>(CS.Afficher_CById(P.getID_publication()));
            System.out.println("Commentaire RESULT : "+List_Commentaire);
//            if (List_Commentaire.size()==0)
//            {
//                HBox_Pub.setVisible(false);
//                scroll_c.setPrefHeight(1);
//                HBox_Pub.setPrefHeight(1);
//                if (P.getFichier().equalsIgnoreCase("NULL") || P.getFichier().length()==0)
//                {   image_pub.setFitHeight(1);
//                    AnchorPanePub.setPrefHeight(100);
//                }
//                else
//                AnchorPanePub.setPrefHeight(365);
//            }
                    if (P.getFichier().equalsIgnoreCase("NULL") || P.getFichier().length()==0)
                    {   image_pub.setFitHeight(1);
                        AnchorPanePub.setPrefHeight(317);
                        HBox_Pub.setTranslateY(-200);
                    }
            for (i=0;i<List_Commentaire.size();i++)
                try {
                //LOADING Comments
                    System.out.println("i : "+i);
                FXMLLoader fxmlLoaderC=new FXMLLoader();
                fxmlLoaderC.setLocation(getClass().getResource("item_Comment.fxml"));
                AnchorPane anchorPane = fxmlLoaderC.load();
                Item_CommentController item_Comment=fxmlLoaderC.getController();
                commentaire C=new commentaire(
                        List_Commentaire.get(i).getID_publication(), 
                        List_Commentaire.get(i).getID_utilisateur(),
                        List_Commentaire.get(i).getMessage());
                item_Comment.setData(C,U);
                grid_c.add(anchorPane,1,i);
            } catch (IOException ex) {
                Logger.getLogger(PublicationInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }

        try {
                FXMLLoader fxmlLoaderCommentItem=new FXMLLoader();
                fxmlLoaderCommentItem.setLocation(getClass().getResource("ItemCommentaire.fxml"));
                AnchorPane anchorPane2 = fxmlLoaderCommentItem.load();
                ItemCommentaireController CC=fxmlLoaderCommentItem.getController();
                //USER SESSION CHANGE ME !
                       utilisateur USession = new utilisateur();
                    USession = getUser();
               CC.SetData(USession,P);
            grid_c.add(anchorPane2,1,i+1);
        } catch (IOException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }

      /* public String HandleMousePub()
       {
          
        return user_action.getItems().get(1).getText();
           
       }*/
    @FXML
    public void HandleMousePub(ActionEvent e) {
     
            System.out.println("Mouse CLicked !");
            System.out.println(user_action.getItems().get(0).getText());
            Parent root2;
               try {
            root2=FXMLLoader.load(getClass().getResource("Pop_upSignaler.fxml"));
            Scene secondScene = new Scene(root2);
            
            // New window (Stage)
            Stage newWindow=new Stage();
           // newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            newWindow2.setUserData(user_action.getItems().get(0).getText());
            newWindow.initStyle(StageStyle.UNDECORATED);
            newWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @FXML
    public void UP(ActionEvent e)
    {
        System.out.println("UP");
    }

    
    void play() {
        System.out.println("EL INDEXOR : "+user_description.getText().indexOf("youtu"));
        if(user_description.getText().toString().indexOf("youtu")>0){
           Matcher matcher = pattern.matcher(this.user_description.getText());
            System.out.println("Hello There YT APIs");
           WebView webV=new WebView();
           webV.setLayoutX(50);
           webV.setLayoutY(50);
           webV.prefWidth(345);
           webV.maxWidth(345);
           webV.maxHeight(194);
           AnchorPanePub.getChildren().add(webV);
            if(matcher.find()){
                this.url ="https://www.youtube.com/embed/"+matcher.group(0);
                webV.getEngine().load(this.url);
                System.out.println(this.url);
            }else{
                System.out.println("Invalid Url!");
            }
        }
    }

    @FXML
    private void openPayment(ActionEvent event) {
        System.out.println("OPEN PAYMENT WORKING");
//        try {       
//               UI_Payment = FXMLLoader.load(getClass().getResource("../../paiement/GUI/paiementinterface.fxml"));
//            } catch (IOException ex) {
//                System.out.println(ex);
//            }
//             Scene scene = new Scene(UI_Payment);   
//            Stage UI_stage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
//       
//            UI_stage.hide();
//            UI_stage.setScene(scene);
//            UI_stage.show();

            System.out.println("Mouse CLicked !");
            System.out.println(user_action.getItems().get(0).getText());
            Parent root2;
               try {
            root2=FXMLLoader.load(getClass().getResource("../../paiement/GUI/paiementinterface.fxml"));
            Scene secondScene = new Scene(root2);
            
            // New window (Stage)
            Stage newWindow=new Stage();
           // newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            newWindow2.setUserData(user_action.getItems().get(0).getText());
            newWindow.initStyle(StageStyle.UNDECORATED);
            newWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
