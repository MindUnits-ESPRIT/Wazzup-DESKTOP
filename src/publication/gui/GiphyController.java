/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.gui;



import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.URL; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 * FXML Controller class
 *
 * @author Misow3002
 */
public class GiphyController {
    @FXML
    private WebView img;
    private WebEngine e;
    @FXML
    private MediaView mediaV;
    @FXML
    private GridPane video_grid;
    @FXML
    private AnchorPane gif_anchor;
    @FXML
    private TextField TypedGIfphy;
    /**
     * Initializes the controller class.
     */

   
//   @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            //            System.out.println("Helloo");
////            // Create a WebView
//////            e=img.getEngine();
////            String Link = null;
////// Load page
//////e.load("https://i.pinimg.com/originals/8a/8f/07/8a8f07c4c49a56b8b05fb5f3b9768ec1.gif");
////        try {
////            //e.load("http://eclipse.com");
////           Link=Determine(call_me());
////        } catch (Exception ex) {
////            Logger.getLogger(GiphyController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////        System.out.println(Link);  
////     e.load(Link);
////     Media media = new Media(Link);
////     MediaPlayer mediaPlayer = new MediaPlayer(media);
////     mediaPlayer.setAutoPlay(true);
////     mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
////     //video=new MediaView(mediaPlayer);
////     video.setMediaPlayer(mediaPlayer);
////    // video.setFitWidth();
////        System.out.println("https://media2.giphy.com/".length());
////        
////        //el 5edma
////        System.out.println();
//OnPressedKey();
//        } catch (Exception ex) {
//            Logger.getLogger(GiphyController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

   
public String[] call_me(String Keyword) throws Exception {
    String url;
    Keyword=java.net.URLEncoder.encode(Keyword,"UTF-8");
    //DISABLED RANDOM GIF
//     if(Keyword.equals("NOKEYWORDENTERED"))
//            {
//         url= "https://api.giphy.com/v1/gifs/random?api_key=58vCKzIZ90aMac7PimlKJR5KcGt39EPo&limit=5&g=1";
//            }else
      url= "https://api.giphy.com/v1/gifs/search?api_key=58vCKzIZ90aMac7PimlKJR5KcGt39EPo&limit=5&q="+Keyword;
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
     //print in String
    // System.out.println(response.toString());
     //Read JSON response and print
     JSONObject myResponse = new JSONObject(response.toString());
  //   System.out.println("result after Reading JSON Response");
   //  System.out.println(myResponse.getJSONObject("pagination"));
    // System.out.println(myResponse.getJSONObject("meta"));
    //Convert Data to ARRAY
     JSONArray  JA =myResponse.getJSONArray("data");
     
     String[] links =new String[JA.length()];
     for (int i=0;i<JA.length();i++)
     {
//System.out.println("HERE ::");
        JSONObject JOI =JA.getJSONObject(i);
   //   System.out.println(JOI.getJSONObject("images"));
   // System.out.println(JOI.getJSONObject("images").getJSONObject("downsized").get("url"));   
    String Link=JOI.getJSONObject("images").getJSONObject("downsized_small").get("mp4").toString();
   links[i]="https://i.giphy.com/"+Link.substring(25);
         System.out.println(links[i]);
      }
        return links;

}

public String  RefinedLink(String  ch)
{
    for (int i=0;i<ch.length();i++)
    {
       if(ch.charAt(i)=='?')
       return ch.substring(0,i);
    }
    return null;
}

public void OnPressedKey(String Keyword) throws Exception
{
    String [] List={};
            if(!(Keyword.equals("NOKEYWORDENTERED")))
            {
            List=call_me(Keyword); 
            }
            System.out.println(List);
            for (int i=0;i<List.length;i++)
            {
                
     String URL=(RefinedLink(List[i]));
     Media media = new Media(URL);
     MediaPlayer mediaPlayer = new MediaPlayer(media);
     mediaPlayer.setAutoPlay(true);
     mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
     MediaView mediaView = new MediaView(mediaPlayer);
     mediaView.setMediaPlayer(mediaPlayer);
     AnchorPane a=new AnchorPane();
     a.getChildren().add(mediaView);
     video_grid.add(a,1,i);


}   
}

public void OnKeyPressEvent() throws InterruptedException
{
    //System.out.println("ZomZING TYPED !");
    video_grid.getChildren().clear();
        try {
           // sleep(2);
            OnPressedKey(TypedGIfphy.getText());
        } catch (Exception ex) {
            Logger.getLogger(GiphyController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
    
