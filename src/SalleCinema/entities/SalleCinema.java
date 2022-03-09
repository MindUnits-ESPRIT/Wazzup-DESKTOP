/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema.entities;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author SRN
 */
public class SalleCinema {
    private int ID_Salle;
    private String NomSalle;
    private String URL_Film;
    private String URL_Salle;
    private JSONArray Chat;

    public SalleCinema() {
    }

    public SalleCinema(int ID_Salle, String NomSalle, String URL_Film, String URL_Salle, JSONArray Chat) {
        this.ID_Salle = ID_Salle;
        this.NomSalle = NomSalle;
        this.URL_Film = URL_Film;
        this.URL_Salle = URL_Salle;
        this.Chat = Chat;
    }

    public SalleCinema(String NomSalle, String URL_Salle) {
        this.NomSalle = NomSalle;
        this.URL_Salle = URL_Salle;
    }

    public SalleCinema(String NomSalle, String URL_Film, String URL_Salle, JSONArray Chat) {
        this.NomSalle = NomSalle;
        this.URL_Film = URL_Film;
        this.URL_Salle = URL_Salle;
        this.Chat = Chat;
    }

    public int getID_Salle() {
        return ID_Salle;
    }

    public void setID_Salle(int ID_Salle) {
        this.ID_Salle = ID_Salle;
    }

    public String getNomSalle() {
        return NomSalle;
    }

    public void setNomSalle(String NomSalle) {
        this.NomSalle = NomSalle;
    }

    public String getURL_Film() {
        return URL_Film;
    }

    public void setURL_Film(String URL_Film) {
        this.URL_Film = URL_Film;
    }

    public String getURL_Salle() {
        return URL_Salle;
    }

    public void setURL_Salle(String URL_Salle) {
        this.URL_Salle = URL_Salle;
    }

    public JSONArray getChat() {
        return Chat;
    }

    public void setChat(JSONArray Chat) {
        this.Chat = Chat;
    }

    @Override
    public String toString() {
        return "SalleCinema{" + "ID_Salle=" + ID_Salle + ", NomSalle=" + NomSalle + ", URL_Film=" + URL_Film + ", URL_Salle=" + URL_Salle + ", Chat=" + Chat + '}';
    }
    
}
