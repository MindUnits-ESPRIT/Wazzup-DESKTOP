/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;


import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author SRN
 */
public class APICinemaInfoController implements Initializable {
       @FXML
    private JFXTextField SearchBar;
           @FXML
    private ImageView poster_img;
         @FXML
    private Label titletxt;

    @FXML
    private Label langtxt;

    @FXML
    private Label statustxt;

    @FXML
    private Label ratetxt;

    @FXML
    private Label genretxt;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void SearchMovie() throws IOException
    {
      //  System.out.println("Oh Hello there ! "+);
        Main M=new Main();
        String S=SearchBar.getText();
            JSONObject R=M.MovieSearch(S);
        System.out.println(R);
        Scrape(M.MovieSearch(S));
        
    }
    public void Scrape(JSONObject myResponse2)
    {
      
         //  System.out.println(myResponse2);
             //JSONObject myOb2=JB.getJSONObject(i);
//                JSONObject myOb=JA.getJSONObject(i);
                titletxt.setText(myResponse2.get("title").toString());
               ratetxt.setText(myResponse2.get("vote_average").toString());
                   langtxt.setText(myResponse2.get("original_language").toString());
                  statustxt.setText(myResponse2.get("status").toString());
                  Image img  =new Image("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+myResponse2.get("poster_path").toString());
                poster_img.setImage(img);
                JSONArray A=myResponse2.getJSONArray("genres");
                String genres="";
                  for(int j=0;j<A.length();j++){ 
                      JSONObject G=A.getJSONObject(j); 
                      genres+=G.getString("name");
                  }
                  genretxt.setText(genres);
                  
    }
     @FXML
    void RetourPage(ActionEvent event) throws IOException {
Parent root=FXMLLoader.load(getClass().getResource("SessionSalleCinema.fxml"));
 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
    }

}
