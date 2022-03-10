/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

import commentaire.entities.commentaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import publication.entities.publication;
import utilisateur.entities.utilisateur;
import publication.services.publicationService;

/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class Pop_upSignalerController {
    
    @FXML
    private ToggleGroup signaler;
    @FXML
    private RadioButton type_signaler;

    @FXML
    private Button btn_envoyer;

    @FXML
    private Button btn_annuler;
    public void CloseBtn(ActionEvent e)
    {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }
    public void btn_envoyer()
    {
                ItemController ic=new ItemController();
                publicationService PS=new publicationService();
                //MUST ADD TYPE
        //SESSION ID USER 
        utilisateur U = new utilisateur(2,"malek","abbes");
        publication P=new publication(Integer.parseInt(ic.newWindow2.getUserData().toString()));
        commentaire C=new  commentaire();
        RadioButton Sel=(RadioButton)signaler.getSelectedToggle();
        C.setMessage(Sel.getText());
          System.out.println("TP : "+Sel.getId());   
           System.out.println("TP : "+Sel.getText());   
        //publication P=new publication(0);
        PS.Signaler_P(P, U,Integer.valueOf(Sel.getId()));
    }
    
}
