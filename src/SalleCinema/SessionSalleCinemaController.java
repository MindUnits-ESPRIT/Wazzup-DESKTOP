package SalleCinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SessionSalleCinemaController implements Initializable{

    @FXML
    private Button Retour;
    
    @FXML
    private WebView webView;

    @FXML
    private Button uploadPage;

    @FXML
    private TextField TextField;
 @FXML
    private WebEngine engine;
 public static final String CHROME_41_USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    private double WebZoom;
    private WebHistory history;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       engine = webView.getEngine();
       TextField.setText("http://w2g.tv");
  engine.load("http://w2g.tv");
   webView.getEngine().setUserAgent(
                CHROME_41_USER_AGENT
        );
   WebZoom=1;
    } 
    @FXML
    void LoadPage(ActionEvent event) {
engine.load("http://"+TextField.getText());
    }

    public void refreshPage(){
    engine.reload();
    }
    public void zoomIn(){
        WebZoom+=0.25;
        webView.setZoom(WebZoom);
    }
    public void zoomOut(){
        WebZoom-=0.25;
     webView.setZoom(WebZoom);
    }
    public void displayHistroy(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries=history.getEntries();
    for ( WebHistory.Entry entry : entries){
        System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
    }
    }
public void back(){
     history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries=history.getEntries();
history.go(-1);
TextField.setText(entries.get(history.getCurrentIndex()).getUrl());
}
public void forward(){
     history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries=history.getEntries();
history.go(1);
TextField.setText(entries.get(history.getCurrentIndex()).getUrl());

}
 public void executeJS(){
   engine.executeScript("window.location = \"https://www.youtube.com\";");
}
  @FXML
    void RetourPage(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("../evenements/afficherEvenement.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }
}
