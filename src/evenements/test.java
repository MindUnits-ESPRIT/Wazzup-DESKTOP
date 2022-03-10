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
        evenementsService es= new evenementsService();
        evenements e =new evenements(5,"Etude",11,"15/3/2022","Rencontre","Salle_publique","Math");
        SalleCinema s=new SalleCinema ();
        Rencontre r = new Rencontre ();
        
     es.ajouter(e);
       
//es.modifier(01, "Revision", 20, "20/02/2022", "SalleCinema", "Salle_publique", "Science");
//es.supprimer(13);
es.afficher(5);

es.modifier(02, "Revision", 20, "20/02/2022", "Etude", "Salle_publique", "Science");
es.supprimer(02);
es.afficher(1);
    }
}
