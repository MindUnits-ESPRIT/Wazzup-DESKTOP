/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.net.*;
import sun.net.www.http.HttpClient;

/**
 *
 * @author SRN
 */
public class Main
{
 
//    HttpRequest request = HttpRequest.newBuilder()
//		.uri(URI.create("https://mdblist.p.rapidapi.com/?s=venom"))
//		.header("x-rapidapi-host", "mdblist.p.rapidapi.com")
//		.header("x-rapidapi-key", "943a75fb6dmshdc7d070cf864494p1705bcjsnddaaaafca5d4")
//		.method("GET", HttpRequest.BodyPublishers.noBody())
//		.build();
//HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//System.out.println(response.body());
//}

public JSONObject MovieSearch(String Keyword) {
     JSONObject myResponse2=null;
        try {
            String url;
            url="https://api.themoviedb.org/3/search/movie?api_key=6b80f937d5dda95453593fb81c36ca9e&query="+Keyword;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
             //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
           // System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);
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
           
            for(int i=0;i<JA.length();i++)
            {
                            url="https://api.themoviedb.org/3/movie/"+JA.getJSONObject(i).getInt("id")+"?api_key=6b80f937d5dda95453593fb81c36ca9e";
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
             //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            responseCode = con.getResponseCode();
           // System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            myResponse2 = new JSONObject(response.toString());
             
                  
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    return myResponse2;

}

}

