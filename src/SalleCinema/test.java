/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema;

import SalleCinema.entities.SalleCinema;
import SalleCinema.services.SalleCinemaService;
import database.db;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author SRN
 */
public class test {
    public static void main(String[] args) {
    // test DB Connexion
        db cnx = db.getInstance();
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
                JSONArray jsonarray = new JSONArray();
    SalleCinemaService ss = new SalleCinemaService();
    JSONObject jsonobject = new JSONObject();
            jsonobject.put("id","1"); 
            jsonarray.add(jsonobject);
            SalleCinema s=new SalleCinema(01, "Movie", "http://example.com/Venom", "http://example.com/Movie",jsonarray);
            ss.ajouter(s);
            ss.modifier(01, "Cinema", "Kung Fu Panda");
            ss.supprimer(01);
            ss.afficher(s.getID_Salle());
    }
    
}
