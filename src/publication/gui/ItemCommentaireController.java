/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import publication.entities.publication;
import publication.services.publicationService;
import utilisateur.entities.utilisateur;
import commentaire.services.commentaireService;
import commentaire.entities.commentaire;
/**
 * FXML Controller class
 *
 * @author Misow3002
 */

public class ItemCommentaireController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView user_image;

    @FXML
    private Label username_pub;

    @FXML
    private TextArea description_pub;
    @FXML
    private Label counter;

    @FXML
    private Button btn_commenter;
        public void CheckForm()
    {
        System.out.println("Event handled!");
       // System.out.println("HELLO ");
        int i=description_pub.getText().length()+1;
        counter.setText(i+"/255");
        System.out.println(i<255);
        if ((i>1) && (i<255))
            btn_commenter.setDisable(false);
        else
            btn_commenter.setDisable(true);
    }
        
        
    public void Commenter()
   {
    String Cleaned="";
    String str=description_pub.getText();
    Anti_BadWords AN=new Anti_BadWords();
    if (str.length()!=0)
    Cleaned=AN.TextAnalyzer(str);
    //   System.out.println("TXT : "+Cleaned);
    //USER SESSION
            utilisateur USession = new utilisateur(2, "Malek", "Abbes");
            publication P=new publication();
            commentaire C=new commentaire();
            int id_Pub=Integer.parseInt(description_pub.getId().substring(15));
            System.out.println("ID :"+id_Pub);
               C.setMessage(description_pub.getText());
               P.setID_publication(id_Pub);
           commentaireService CS=new commentaireService();
          // System.out.println("The Message : "+description_pub.getText());
     CS.Creer_C(P, C, USession);
   }
    
    public void SetData(utilisateur U,publication P)
       {
      username_pub.setText(U.getNom()+" "+U.getPrenom());
      description_pub.setId("description_pub"+P.getID_publication());
       }
}
