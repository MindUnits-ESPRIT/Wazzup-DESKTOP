/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;
import Rencontre.entities.Rencontre;
import SalleCinema.entities.SalleCinema;
import database.db;
import evenements.entities.*;
import evenements.services.*;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author malek
 */
public class test {
     public static void main(String[] args) {
         JSONArray jsonarray = new JSONArray();
        // test DB Connexion
        db cnx = db.getInstance();
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
                 JSONObject jsonobject = new JSONObject();
            jsonobject.put("id","1"); 
            jsonarray.put(jsonobject);
        evenementsService es= new evenementsService();
        evenements e =new evenements(jsonarray,01,"01/02/2022",01,"Etude",11,"01/02/2022","Etude","Salle_publique","Math");
        SalleCinema s=new SalleCinema ();
        Rencontre r = new Rencontre ();
        
        es.ajouter(e);
        
    
es.modifier(02, "Revision", 20, "20/02/2022", "Etude", "Salle_publique", "Science");
es.supprimer(02);
es.afficher(1);
    }
}
