package collab.gui;
import collab.entities.Projet;
import collab.entities.Salle_Collaboration;
import collab.entities.chat;
import collab.services.ChatService;
import collab.services.CollabService;
import collab.services.ProjetService;
import database.db;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import utilisateur.entities.utilisateur;
import static utils.SessionUser.getUser;
import utils.mail;
//@author mouhi
public class PCollabController implements Initializable {
    int iduser =getUser().getID_Utilisateur();
Parent collab_page_home;
    @FXML
    private Button retour;
    ObservableList<utilisateur> users = FXCollections.observableArrayList();
      db cnx = db.getInstance();
        CollabService cs = new CollabService();
        ProjetService ps = new ProjetService();  
        ChatService chatu = new ChatService();
    @FXML
    public TableView<utilisateur> tab;
    @FXML
    public TableColumn<utilisateur, String> nom;
    @FXML
    private Button deleteC;
    @FXML
    private Label errarea;
    @FXML
    public TableColumn<utilisateur, String> idm;
    @FXML
    private ImageView qr;
    @FXML
    public AnchorPane AP;
    @FXML
    private javafx.scene.control.TextField msg;
    @FXML
    private Button send;
    @FXML
    private Tab projettab;
    @FXML
    private AnchorPane ap_main;
    @FXML
    private VBox vbox_messages;
   
    @FXML
    private ScrollPane sp_main;
    @FXML
    private Tab newprjttab;
    @FXML
    private TabPane tabpane;
    int r = 0 ;
    int i =0 ;
    
    @FXML
    private Label HeaderTxt;
    @FXML
    private TextField tk;
    @FXML
    private TextField idp;
    @FXML
    private TextField tt;
    @FXML
    private TextArea dp;
    @FXML
    private Hyperlink hyperKey;
    @FXML
    private WebView webs;
    @FXML
    private ImageView closex;
    @FXML
    private Hyperlink hyperKey2;
    @FXML
    private Label errLabel;
    Projet p = new Projet();
     Salle_Collaboration s = new Salle_Collaboration();
    @FXML
    private Label prtLabel;
    @FXML
    private Button deleteC1;
    @FXML
    private Label descLab;
    @FXML
    private Hyperlink trelloHyper;
    @FXML
    private WebView webs2;
    @FXML
    private ImageView closex2;
    @FXML
    private AnchorPane ANP1;
    @FXML
    private SplitPane confirmpanel1;
    @FXML
    private Button annuler1;
    @FXML
    private Button confirmer1;
    int detect = 0;
    ObservableList<chat> listu ;
    @FXML
    private Button verif;
    @FXML
    private Label grramr;
    @FXML
    private Button traduire;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabpane.getTabs().remove(newprjttab); 
        sp_main.setFitToWidth(true);
  //creating a new server object for the chat
 
     //  socket();
      vbox_messages.heightProperty().addListener(new ChangeListener<Number>(){
      @Override
      public void changed(ObservableValue<? extends Number> observable,Number oldValue,Number newValue){
       sp_main.setVvalue((double) newValue);
      }   
      });
      
//      server.receiveMessageFromClient(vbox_messages);         
// wait fonction so the scene is loaded and we can get its refernce to get the sent collab object from the previous scene
     new java.util.Timer().schedule(          
        new java.util.TimerTask() {
            @Override
            public void run() {
               
                 Stage cstage = (Stage) tabpane.getScene().getWindow();
                 
                 s = (Salle_Collaboration) cstage.getUserData();
                 System.out.println(s.getID_Collab());
                 users = cs.afficherCollab_Membres(s.getID_Collab());  
                
                 ObservableList<utilisateur> listo = (ObservableList<utilisateur>) users ;
                 nom.setCellValueFactory(new PropertyValueFactory<utilisateur , String>("nom")); 
                 idm.setCellValueFactory(new PropertyValueFactory<utilisateur , String>("ID_Utilisateur"));
                  
                 tab.setItems(listo); 
                 p = ps.afficher(s.getID_Collab());
                 if(p.getNom_Projet() == null){
                  String msg = s.getNom_Collab() +"\n"+s.getURL_Collab();      
                 mail.QRCode(msg,qr);  
                 }
             else{  String msg = s.getNom_Collab() +"\n"+s.getURL_Collab()+"\n"+p.getURL_Trello();      
                 mail.QRCode(msg,qr);                               
                 }
                  
                 
            }},50);
     
       Platform.runLater(new Runnable() {
            @Override
            public void run() {
               
           listu = chatu.afficher(s.getID_Collab());           
               listu.forEach((chat u) -> {   
                 
                    String mssg = u.getNom()+": "+u.getBody();
                    HBox hBox = new HBox ();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));
                    Text text = new Text(mssg);
                    TextFlow textFlow = new TextFlow(text); 
                    textFlow.setStyle("-fx-color: rgb(239,242,255);"+ 
                     "-fx-background-color:#008080;"+
                     "-fx-background-radius: 20px;"
                   );
                     textFlow.setPadding(new Insets(5,10,5,10));
                     text.setFill(Color.color(0.934, 0.945, 0.996));
                       if(u.getID_Uitlisateur()== iduser){
                        hBox.getChildren().add(textFlow);
                        vbox_messages.getChildren().add(hBox);       
                       }else{
                      addLabel(u.getNom()+": "+u.getBody(),vbox_messages); 
                      
                       }         
               }); 
                  
            }});
     
          Timer timer = new Timer();
            timer. schedule(new TimerTask() {
                @Override
            public void run() {
              Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
               vbox_messages.getChildren().clear();
               listu = chatu.afficher(s.getID_Collab());           
               listu.forEach((chat u) -> {   
                 
                    String mssg = u.getNom()+": "+u.getBody();
                    HBox hBox = new HBox ();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));
                    Text text = new Text(mssg);
                    TextFlow textFlow = new TextFlow(text); 
                    textFlow.setStyle("-fx-color: rgb(239,242,255);"+ 
                     "-fx-background-color:#008080;"+
                     "-fx-background-radius: 20px;"
                   );
                     textFlow.setPadding(new Insets(5,10,5,10));
                     text.setFill(Color.color(0.934, 0.945, 0.996));
                       if(u.getID_Uitlisateur()== iduser){
                        hBox.getChildren().add(textFlow);
                        vbox_messages.getChildren().add(hBox);       
                       }else{
                      addLabel(u.getNom()+": "+u.getBody(),vbox_messages); 
                      
                       }         
               }); 
                  
            }});
              
}
}, 50, 500);//wait 50 ms before doing the action and do it evry 500ms (0.5second)
    }      
 // routing fonction to the previous scene   
    @FXML
    private void returnb(ActionEvent event) {
         try {       
               collab_page_home = FXMLLoader.load(getClass().getResource("CollabListe.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CollabwController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Scene scene = new Scene(collab_page_home);      
            Stage CfStage = (Stage) (((Node) event.getSource()) .getScene().getWindow());
            CfStage.hide();
            CfStage.setScene(scene);
            CfStage.show();}
// delete member fonction
    @FXML
    private void DeleteC(ActionEvent event) {
     
         if (tab.getSelectionModel().getSelectedItems().isEmpty()) {
            errarea.setText("Selectionner un membre"); }
        else {      
             Stage cstage = (Stage) AP.getScene().getWindow();
             Salle_Collaboration s = new Salle_Collaboration();
              s = (Salle_Collaboration) cstage.getUserData();       
             if (s.getID_Utilisateur()!=iduser){
             cs.Notificationmanager(8);
        }
        else { 
               
         if(tab.getSelectionModel().getSelectedItems().get(0).getID_Utilisateur()==s.getID_Utilisateur()){
         cs.Notificationmanager(9);
         }   
         
         else{
               ANP1.setVisible(true);
               detect =1;
            }  
                 
       
        }}}

    @FXML
    private void send(ActionEvent event) {
        String messageToSend = msg.getText();
        msg.clear();
        if(!messageToSend.isEmpty()){
           HBox hBox = new HBox ();
           hBox.setAlignment(Pos.CENTER_RIGHT);
           hBox.setPadding(new Insets(5,5,5,10));
           utilisateur u =cs.getinfo2(iduser);
           Text text = new Text( u.getNom()+": "+messageToSend);
           TextFlow textFlow = new TextFlow(text); 
           textFlow.setStyle("-fx-color: rgb(239,242,255);"+ 
                  "-fx-background-color:#008080;"+
                  "-fx-background-radius: 20px;"
                   );
           textFlow.setPadding(new Insets(5,10,5,10));
           text.setFill(Color.color(0.934, 0.945, 0.996));
           hBox.getChildren().add(textFlow);
           vbox_messages.getChildren().add(hBox);
           chat ch = new chat();
                  ch.setID_Collab(s.getID_Collab());
                  ch.setID_Uitlisateur(iduser);
                  ch.setBody(messageToSend);
           chatu.send(ch);
          
            
           
        }
    }
    
    public static void addLabel(String messageFromClient, VBox vbox){     
    HBox hBox = new HBox ();
           hBox.setAlignment(Pos.CENTER_LEFT);
           hBox.setPadding(new Insets(5,5,5,10));
           Text text = new Text(messageFromClient);
            TextFlow textFlow = new TextFlow(text); 
           textFlow.setStyle("-fx-background-color: rgb(233,223,235);"+
                  "-fx-background-radius: 20px;"
                   );
           textFlow.setPadding(new Insets(5,10,5,10));
           
           hBox.getChildren().add(textFlow);
           vbox.getChildren().add(hBox);
       
    }
    
    
     public  void SetData(TabPane tabpane,ProjetService ps,Tab newprjttab,Tab projettab,Salle_Collaboration s,int i){     
        
               if(r==0){
                   r=2;
                   p=ps.afficher(s.getID_Collab());
                  if (p.getNom_Projet()== null){ 
                    tabpane.getTabs().add(newprjttab);
                    tabpane.getTabs().remove(projettab);   
                    cs.Notificationmanager(4);
                    }else{  
                      System.out.println(ps.afficher(s.getID_Collab()));                     
                      tabpane.getTabs().remove(newprjttab); 
                      prtLabel.setText(p.getNom_Projet());
                      descLab.setText(p.getDescription());
                      
                      HeaderTxt.setText("Wazzup Project");
                }
               }
    }
   

    @FXML
    private void fetchandcheck(Event event) {
         Stage cstage = (Stage) tabpane.getScene().getWindow();
                 Salle_Collaboration s = new Salle_Collaboration(); 
                  s = (Salle_Collaboration) cstage.getUserData();
                  if (i == 0){
                      i=2;
                      SetData(tabpane, ps, newprjttab,projettab,s,i);}
                if ( r>0){
                HeaderTxt.setText("Wazzup Project");
                }
    }

    @FXML
    private void chataction(Event event) {
      if(i!=0){
       HeaderTxt.setText("Collaboration Chat");
      } 
    }

    @FXML
    private void newProjectaction(Event event) {
         HeaderTxt.setText("Create Project");
    }

    @FXML
    private void createP(ActionEvent event) {
        if(idp.getText().isEmpty()){   
        errLabel.setText("Choisir un nom");       
        }
        else{
        if(tt.getText().isEmpty()){   
        errLabel.setText("Entrer le token");       
        }
        else{
        if(tk.getText().isEmpty()){   
        errLabel.setText("Entrer le key");       
        }
        else{
         if(dp.getText().isEmpty()){   
        errLabel.setText("Ecrire une description");       
        }
         else{
         String Token = tt.getText();
              String Key = tk.getText();
              String name = idp.getText();
              String desc = dp.getText();
                HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
                .queryString("name", name)
                .queryString("key", Key)
                .queryString("token", Token)
                .asString();          
                  String str =  response.getBody();
                  if(str.equals("invalid token")||str.equals("invalid key")){
                  errLabel.setText("Key or token are not valid");
                  }
                  else{ JSONObject jsonObject = new JSONObject(str);
                  System.out.println(jsonObject.get("url")); 
                  p.setDescription(desc);
                  p.setNom_Projet(name);
                  p.setID_Collab(s.getID_Collab());
                  p.setURL_Trello(jsonObject.get("url").toString());
                  ps.creer(p);
                 cs.Notificationmanager(10);
                 tabpane.getTabs().remove(newprjttab);
                 tabpane.getTabs().add(projettab);
                      HeaderTxt.setText("Wazzup Collaborations");
                   prtLabel.setText(p.getNom_Projet());
                      descLab.setText(p.getDescription());}
                 
         
         }}}}}   
    @FXML
    private void getkey(ActionEvent event) {
        webs.setVisible(true);
        WebEngine webEngine = webs.getEngine();
        String url =  "https://trello.com/app-key/";
       
                    webEngine.load(url);
                    closex.setVisible(true);
        
        
    }

    @FXML
    private void closeweb(MouseEvent event) {
        webs.setVisible(false);
        closex.setVisible(false);
    }

    @FXML
    private void gettoken(ActionEvent event) {
         webs.setVisible(true);
         WebEngine webEngine = webs.getEngine();
         String url =  "https://trello.com/1/authorize?expiration=never&scope=read,write,account&response_type=token&name=Server%20Token&key=e0efb3bcbe6f5dbf936f8c2e883a9580";
         
       
                    webEngine.load(url);
                    closex.setVisible(true);
        
    }

    @FXML
    private void DeleteP(ActionEvent event) {
        detect=2;
          ANP1.setVisible(true);
               
        
                   
        
    }

    @FXML
    private void TrelloHyperAC(ActionEvent event) {
         webs2.setVisible(true);
        WebEngine webEngine = webs2.getEngine();
        
        String url =  p.getURL_Trello();
       
                    webEngine.load(url);
                    closex2.setVisible(true);   
        
    }
 
    @FXML
    private void closeweb1(MouseEvent event) {
        webs2.setVisible(false);
        closex2.setVisible(false);
    }

    @FXML
    private void Annuler(ActionEvent event) {
         ANP1.setVisible(false);
         detect =0;
    }

    @FXML
    private void Confirmer(ActionEvent event) {
        if(detect == 1){
            detect =0;
            ANP1.setVisible(false);
            cs.supprimer_membre(s.getID_Collab(),tab.getSelectionModel().getSelectedItems().get(0).getID_Utilisateur());
        cs.Notificationmanager(1);
        users = cs.afficherCollab_Membres(s.getID_Collab());     
        ObservableList<utilisateur> listo = (ObservableList<utilisateur>) users ;
        nom.setCellValueFactory(new PropertyValueFactory<utilisateur , String>("nom")); 
        idm.setCellValueFactory(new PropertyValueFactory<utilisateur , String>("ID_Utilisateur"));
        tab.setItems(listo);
        }
        else{
        if(detect ==2){
             detect =0;
            ANP1.setVisible(false);
        ps.supprimer(p.getID());
        cs.Notificationmanager(1);
         tabpane.getTabs().remove(projettab);
         tabpane.getTabs().add(newprjttab);
        }
        }
        
    }

    @FXML
    private void verify(ActionEvent event) {
        if (!msg.getText().isEmpty()){
        String msgV = msg.getText();
     HttpResponse<String> response = Unirest.post("https://jspell-checker.p.rapidapi.com/check")
	.header("x-rapidapi-host", "jspell-checker.p.rapidapi.com")
	.header("x-rapidapi-key", "219e7ed17cmsh763d3d5b14453f2p146e44jsnb9dcbbef176c")
             .body("{	\"language\": \"frFR\",\n" +
"	\"fieldvalues\": \"'"+msgV+"'\" ,\n" +
"	\"config\": {\n" +
"		\"forceUpperCase\": false,\n" +
"		\"ignoreIrregularCaps\": false,\n" +
"		\"ignoreFirstCaps\": true,\n" +
"		\"ignoreNumbers\": true,\n" +
"		\"ignoreUpper\": false,\n" +
"		\"ignoreDouble\": false,\n" +
"		\"ignoreWordsWithNumbers\": true\n" +
"	}}")
	.asString();
     String str =  response.getBody();
                 JSONObject jsonObject = new JSONObject(str);
                 JSONArray js = (JSONArray) jsonObject.get("elements");  
                String s=String.valueOf(jsonObject.get("spellingErrorCount"));
                System.out.println(s);
                String mmg = "Ilya "+s+" erreur: *" ;
                 for (int i=0; i < js.length(); i++) {
                  JSONObject j= js.getJSONObject(i);    
                    
                     JSONArray js1 = j.getJSONArray("errors") ;
                       for (int l=0; l < js1.length(); l++) {
                             JSONObject j2= js1.getJSONObject(l);
                              JSONArray j3=j2.getJSONArray("suggestions") ;                           
                              mmg=mmg+(String) j2.get("word")+": ";
                             for (int k = 0; k < 2; k++) {
                                 if(k<1)
                               mmg=mmg+j3.getString(k)+"/";
                                 else
                                      mmg=mmg+j3.getString(k)+" *";
                           }
                       }
                       System.out.println(mmg);
                       grramr.setText(mmg);                    
}
    }
    else {
       grramr.setText("Rien pour corriger");
}}

    @FXML
    private void trad(ActionEvent event) {
          if (!msg.getText().isEmpty()){
        String msgV = msg.getText();
        msgV=msgV.replaceAll(" ", "%20");
              System.out.println(msgV);
  HttpResponse<String> response = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
	.header("content-type", "application/x-www-form-urlencoded")
	.header("accept-encoding", "application/gzip")
	.header("x-rapidapi-host", "google-translate1.p.rapidapi.com")
	.header("x-rapidapi-key", "219e7ed17cmsh763d3d5b14453f2p146e44jsnb9dcbbef176c")
	.body("q='"+msgV+"'&target=fr&source=en")
	.asString();
     String str =  response.getBody();
                   
                 JSONObject jsonObject = new JSONObject(str);
               //  System.out.println(str);
                 JSONObject j = (JSONObject) jsonObject.get("data");
                // System.out.println(j);
                 JSONArray j1 = j.getJSONArray("translations");
                // System.out.println(j1);
                 JSONObject j5 = (JSONObject) j1.get(0);
                 String s1 = j5.getString("translatedText");
                 
                s1 = s1.replaceAll("&#39;", "");
                System.out.println(s1);
                grramr.setText(s1);
                msg.clear();
                msg.setText(s1);
    } 
    else {
       grramr.setText("Rien pour traduire");
}
        
    }
    
}
