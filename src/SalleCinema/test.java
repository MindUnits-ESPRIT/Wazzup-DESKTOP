/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;

import SalleCinema.entities.SalleCinema;
import SalleCinema.services.SalleCinemaService;
import database.db;
import evenements.entities.evenements;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import evenements.URL.URL;
import java.sql.SQLException;
import utilisateur.entities.utilisateur;

/**
 *
 * @author SRN
 */
public class test {
    public static void main(String[] args) throws SQLException {
    // test DB Connexion
        db cnx = db.getInstance();
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
                JSONArray jsonarray = new JSONArray();
    SalleCinemaService ss = new SalleCinemaService();
    URL u = new URL();
    JSONObject jsonobject = new JSONObject();
            jsonobject.put("id","5"); 
            jsonarray.add(jsonobject);
            SalleCinema s=new SalleCinema("Movie", "http://example.com/Venom", u.GetUrl_SalleCinema(5),jsonarray);
            evenements e =new evenements(33);
            utilisateur ut = new utilisateur();
            System.out.println( u.GetUrl_SalleCinema(5));
           //ss.ajouter(s,e);
           // ss.modifier(02, "Cinema", "Kung Fu Panda");
            //ss.supprimer(02);
           ss.afficher(5);
    }
    
}
