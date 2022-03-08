/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import database.db;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;
import facture.services.factureService;
/**
 * FXML Controller class
 *
 * @author SBS
 */
public class FactureFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    Connection cnx;
    public PreparedStatement pste;
    public ResultSet result;

      @FXML
    private AnchorPane AnchorPane;

    @FXML
    private VBox BVoxleftside;

    @FXML
    private TextField txtnumfac;

    @FXML
    private ComboBox<String> ComboBoxFacture;

    @FXML
    private Button Buttonimprimer;

    @FXML
    private ImageView logoimprim;

    @FXML
    private Text numfac;

    @FXML
    private Text nomoffre;

    @FXML
    private TextField txtnomoffre;

    @FXML
    private Text nomutilisateur;

    @FXML
    private TextField txtnomutilisateur;

    @FXML
    private Text email;

    @FXML
    private TextField txtemail;

    @FXML
    private Text prix;

    @FXML
    private TextField txtprix;
        @FXML
    private ImageView img;
    @FXML
    private Text veriftext;   
 
 
    
     @FXML
    public void remplirdate(){
factureService fS=new factureService();

    List<String> facture = new ArrayList<>(fS.remplirdate());
         System.out.println("Fac"+facture);

    ComboBoxFacture.setItems(FXCollections.observableArrayList(facture));   
    } 

    
    
    @FXML
void imprimer() throws BadElementException, IOException{Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
    Document docu=new Document();
if(txtnumfac.getText().isEmpty()){
    Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ERROR");
                alert.setContentText("Facture introuvable veuillez sélectionner votre date facture");

		alert.showAndWait();
        } else {
    try{
        PdfWriter.getInstance(docu, new FileOutputStream("Facturewazz.pdf"));
        docu.open();
        String format="dd/mm/yy hh:mm";
        SimpleDateFormat formater=new SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        com.itextpdf.text.Image img=com.itextpdf.text.Image.getInstance("src\\paiement\\img\\Mou7awla3 (1).png");
        img.scaleToFit(200f, 200f);
        img.setAlignment(com.itextpdf.text.Image.ALIGN_LEFT);
        docu.add(img);
        docu.add(new Paragraph("\n Merci d'avoir effectué vos achats sur WAZZUP le "+formater.format(date), blueFont));
        docu.add(new Paragraph("Facture numero : "+txtnumfac.getText()+""
        +"\n Votre offre est "+txtnomoffre.getText()+""

        + "\n sur le nom "+txtnomutilisateur.getText()+""       
        + "\n Votre EMAIL : "+txtemail.getText()+"" 
         + "\n Montatant total : "+txtprix.getText()+"" ));
        docu.close();
        Desktop.getDesktop().open(new File("Facturewazz.pdf"));
    }catch (FileNotFoundException | DocumentException e){
        e.printStackTrace();
        System.out.println(e);
    
    }
}  
}

    
    
@FXML
void search(){
    String id=ComboBoxFacture.getValue();
    String sql="SELECT num_fac,nom_offre,nom,email,prix FROM facture f,offre_publicitaire o,utilisateurs u,paiement p WHERE u.ID_Utilisateur=f.ID_Utilisateur and f.ID_paiement=p.ID_paiement AND f.id_offre=o.id_offre AND Date_fac='"+id+"'";
        try {
            pste=cnx.prepareStatement(sql);
            result=pste.executeQuery();
            byte byteimg[];
            Blob blob;
            if(result.next()){
            txtnumfac.setText(result.getString("num_fac"));
            txtnomoffre.setText(result.getString("nom_offre"));
            txtnomutilisateur.setText(result.getString("nom"));
            txtemail.setText(result.getString("email"));
            txtprix.setText(result.getString("prix"));
           //Image img=new Image(new ByteArrayInputStream(byteimg),ImageLog.getFitWidth(),imageLog.FitHeight(),true,true);
            
            
            img.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
}
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       cnx= db.getInstance().getCnx();
        //remplirdate();
       // rempliroffre();

        
    }
}