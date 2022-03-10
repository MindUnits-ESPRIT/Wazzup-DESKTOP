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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import commentaire.entities.commentaire;
import utilisateur.entities.utilisateur;

/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class Item_CommentController {

    @FXML
    private ImageView user_image;

    @FXML
    private Label username_comment;

    @FXML
    private Label comment_text;
    /**
     * Initializes the controller class.
     */
 public void setData(commentaire C,utilisateur U)
        {
            username_comment.setText(U.getNom()+" "+U.getPrenom());
            comment_text.setText(C.getMessage());
            
        } 
    
}
