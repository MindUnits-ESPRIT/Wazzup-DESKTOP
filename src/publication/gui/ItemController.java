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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.swing.Spring.width;
import publication.entities.publication;
import utilisateur.entities.utilisateur;

/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class ItemController {
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
    int i;
    utilisateur USession = new utilisateur("spx","tn",23,24664800,"malek.abbes@esprit.tn","hellotest","User",4,"Male");
        public void setData(publication P,utilisateur U,String str)
        {
            user_action.getItems().get(0).setText(valueOf(P.getID_publication()));
            pub_date.setText("Publié "+P.getDate().substring(0,16));
            user_name.setText(U.getNom()+" "+U.getPrenom());
            user_description.setText(P.getDescription());
            
            if (P.getFichier().equalsIgnoreCase("NULL") || P.getFichier().length()==0)
            {
                image_pub.setVisible(false);
                int X=(int) user_description.getTranslateX();
                int Y=(int) user_description.getTranslateY()-150;
                System.out.println(X);
                jaime_btn.setTranslateX(X-50);
                jaime_btn.setTranslateY(Y+25);
                commenter_btn.setTranslateX(X-50);
                commenter_btn.setTranslateY(Y+25);
                partager_btn.setTranslateX(X-30);
                partager_btn.setTranslateY(Y+25);
            }
            else
            {
                image_pub.setVisible(true);
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
    public void UP(ActionEvent e)
    {
        System.out.println("UP");
    }

}
