/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;


import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void SearchMovie()
    {
      //  System.out.println("Oh Hello there ! "+);
        Main M=new Main();
        String S=SearchBar.getText();
        System.out.println(S);
        Scrape(M.MovieSearch(S));
        
    }
    public void Scrape(JSONObject myResponse2)
    {
      
         //  System.out.println(myResponse2);
             //JSONObject myOb2=JB.getJSONObject(i);
//                JSONObject myOb=JA.getJSONObject(i);
                System.out.println(myResponse2.get("title"));
                System.out.println(myResponse2.get("vote_average"));
                   System.out.println(myResponse2.get("original_language"));
                   System.out.println(myResponse2.get("status"));
                System.out.println("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+myResponse2.get("poster_path"));
                JSONArray A=myResponse2.getJSONArray("genres");
                  for(int j=0;j<A.length();j++){ 
                      JSONObject G=A.getJSONObject(j); 
                  System.out.println(G.getString("name")+",");
                  }
                  
    }
}
