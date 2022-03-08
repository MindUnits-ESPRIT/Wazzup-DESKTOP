/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import publication.entities.publication;
import publication.services.publicationService;
import utilisateur.entities.utilisateur;
import utilisateur.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class PublicationInterfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private GridPane grid;
     @FXML
    private GridPane grid_profile;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Pane profile_pane;
    @FXML
    private Pane acceuil_pane;
    @FXML
    private Pane searchPane;
    @FXML
    private TextField Searchbar;
    @FXML
    private ListView<String> search_list;
int IJK=1;
int LoadCounter=0;
    @FXML
    private ImageView loading_img;
    List <String> lol=new ArrayList<>();
    @FXML
    private ScrollPane scroll1;
        private List<String> FillArray()
        {
            List <String> lol=new ArrayList<String>();
            for (int i=0;i<10;i++)
            {
                lol.add("SA77A Hama : "+i);
            }
            return lol;
        }

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column=0;
        Load_Publications("ALL");    
    }
    public void Load_Publications(String str)
    {
//        Stage S;
//        System.out.println(S.getUserData());
      
//OPEN  
       // grid_profile.getChildren().clear();
        
         utilisateur USession = new utilisateur("spx","tn",23,24664800,"malek.abbes@esprit.tn","hellotest","User",4,"Male");
                 try {
         System.out.println("Visited FROM : "+ str);
            FXMLLoader fxmlLoaderPostPub=new FXMLLoader();
            fxmlLoaderPostPub.setLocation(getClass().getResource("Post_Publication.fxml"));
            AnchorPane anchorPostPub = fxmlLoaderPostPub.load();
            Post_PublicationController PostPubController =fxmlLoaderPostPub.getController();
            PostPubController.SetData(USession);
          // AnchorPane anchorPostPub = fxmlLoaderPostPublication.load();
            if (str=="ALL")
            grid.add(anchorPostPub,1,0);
            else
            grid_profile.add(anchorPostPub,1,0);
           // fxmlLoaderPostPublication.getController()

        } catch (IOException ex) {
            Logger.getLogger(PublicationInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

 Timer timer = new Timer();
            timer. schedule(new TimerTask() {
                @Override
            public void run() {
              Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
                
        publicationService PS = new publicationService();
        UtilisateurService SS=new UtilisateurService();
        List <publication> list_pub;
        if (str=="ME")
        { //SESSION ID KEY HERE >>
            System.out.println("hello profile");
         utilisateur u = new utilisateur(9,"malek","abbes");
         list_pub=new ArrayList<publication>(PS.Afficher_P(u));
        }
        else
        {System.out.println("hello everyone");
            list_pub=new ArrayList<publication>(PS.Afficher_P());}
         List <utilisateur> list_utilisateur=new ArrayList<utilisateur>();
         System.out.println(list_pub);
            for (int i=0;i<list_pub.size();i++)
            {

                publication P = new publication(
                        list_pub.get(i).getID_publication(),
                        list_pub.get(i).getID_utilisateur(),
                        list_pub.get(i).getDescription(),
                        list_pub.get(i).getFichier(),
                        list_pub.get(i).getDate());
                list_utilisateur=SS.afficherParID(list_pub.get(i).getID_utilisateur());
                utilisateur U = new utilisateur(list_utilisateur.get(0).getID_Utilisateur(),
                        list_utilisateur.get(0).getNom(),
                        list_utilisateur.get(0).getPrenom());
            //    System.out.println("Li 7achtik Bihh "+SS.afficherParID(list_pub.get(i).getID_utilisateur()));
               
                
            try {
                    // LOADING Publications
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane=fxmlLoader.load();
                ItemController itemController =fxmlLoader.getController();
                itemController.setData(P,U,str);
                if (str=="ALL")
                    {   if (LoadCounter>1)
                    {//grid.getChildren().remove(i+1);
                    System.out.println("Removed Index GRID : +"+i);}
                        grid.add(anchorPane,1,i+1);}
                else
                {   if (LoadCounter>1)
                   // grid_profile.getChildren().remove(i+1);
                    grid_profile.add(anchorPane,1, i+1);
                    System.out.println("Helo Profile Grid");}
              //  grid.setMargin(anchorPane,new Insets(-15));

             // break;
            } catch (IOException ex) {
                Logger.getLogger(PublicationInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            LoadCounter++;
   }});
}
},2000, 10000);
    }
    @FXML
        public void Acceuil_SHow(ActionEvent e) throws InterruptedException
    {
        profile_pane.setVisible(false);
        grid_profile.getChildren().clear();
        grid.getChildren().clear();
        acceuil_pane.setVisible(true);
       // sleep(2);
        Load_Publications("ALL");         
    } 
        
    @FXML
         public void Profile_Show(ActionEvent e) throws InterruptedException
    {
        acceuil_pane.setVisible(false);
        grid.getChildren().clear();
        grid_profile.getChildren().clear();
        profile_pane.setVisible(true);
       // sleep(2);
 Load_Publications("ME"); 
    } 

   public void Retour_Show(ActionEvent e)
    {
        System.out.println("RETURN TRUE");
    }
    @FXML
   public void SearchForSomeone()
   {
       if(Searchbar.getText().length()!=0)
       {
         loading_img.setVisible(true);
           searchPane.setVisible(true);      
           search_list.getItems().clear();
           IJK=1;
            new java.util.Timer().schedule(
           new java.util.TimerTask() {
               
               @Override
               public void run() {
                   if(IJK==1)
                   {afficherParNomPrenom();}
                   IJK++;
   }},1000);        
       }
       else
       {
           searchPane.setVisible(false);
           loading_img.setVisible(true);
       }
   }
   public void afficherParNomPrenom()
   {
       loading_img.setVisible(false);
              UtilisateurService SS=new UtilisateurService();
              String Name=Searchbar.getText();
       List <utilisateur> list_utilisateur=new ArrayList<utilisateur>(SS.afficherParID(Name,Name));
           for (int i=0;i<list_utilisateur.size();i++)
            {
               search_list.getItems().add(list_utilisateur.get(i).getNom()+" "+list_utilisateur.get(i).getPrenom());
            }
    }

   public void SearchLostFocus()
    {
        System.out.println("LOST");
        searchPane.setVisible(false); 
    }
      public void SearchInFocus()
    {
         System.out.println("IN");
        searchPane.setVisible(true); 
    }
}
