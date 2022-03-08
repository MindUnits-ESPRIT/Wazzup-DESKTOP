/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;

import com.pavlobu.emojitextflow.EmojiTextFlow;
import com.vdurmont.emoji.EmojiParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class EmojiController implements Initializable{

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private GridPane emoji_grid;
         @FXML
    private TextField TypedEmoji;
public String[] call_me_emoji(String Keyword) throws Exception {
    String url;
    emoji_grid.getChildren().clear();
        if (Keyword.length()!=0)
       url="https://api.emojisworld.fr/v1/search?q="+Keyword+"&categories=1,2,3&sub_categories=1,2,3&limit=50";
            else
        url="https://api.emojisworld.fr/v1/random?&categories=1,2,3&sub_categories=1,2,3&limit=50";
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     // optional default is GET
     con.setRequestMethod("GET");
     //add request header
     con.setRequestProperty("User-Agent", "Mozilla/5.0");
     int responseCode = con.getResponseCode();
     System.out.println("\nSending 'GET' request to URL : " + url);
     System.out.println("Response Code : " + responseCode);
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
     JSONObject myResponse = new JSONObject(response.toString());
     JSONArray  JA =myResponse.getJSONArray("results"); 
     String[] links =new String[JA.length()];
     int row=0;
     int column=0;
     for (int i=0;i<JA.length();i++)
     {
        JSONObject JOI =JA.getJSONObject(i);
      System.out.println(JOI.get("emoji"));
AnchorPane PP=new AnchorPane();
Text TX=new Text();
TX.setText(EmojiParser.parseToUnicode(EmojiParser.parseToAliases(JOI.get("emoji").toString())));
PP.getChildren().add(TX);
TX.setFont(Font.font("OpenSansEmoji", FontWeight.NORMAL, FontPosture.REGULAR, 20));
if (column==10)
{  row++;
    column=0;
}
emoji_grid.add(PP,column,row);

column++;
     //   TextEmojii.setText();
     //emojiLoader.
 //   textoo.setText();
   // System.out.println(JOI.getJSONObject("images").getJSONObject("downsized").get("url"));   
    //String Link=JOI.getJSONObject("emoji").toString();
     //    System.out.println(Link);
      //   System.out.println(EmojiParser.parseToUnicode(EmojiParser.parseToAliases(JOI.get("emoji").toString())));
      }
        return links;

}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//    try {
//      //  System.out.println("\uD83D\uDC7D");
//        call_me_emoji();
//    } catch (Exception ex) {
//        Logger.getLogger(EmojiController.class.getName()).log(Level.SEVERE, null, ex);
//    }
    }
    
    public void TypedEmoji() throws Exception
    {
        
        call_me_emoji(TypedEmoji.getText().toString());
    }
    
}
