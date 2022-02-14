/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;
import database.db;
import evenements.entities.*;
import evenements.services.*;

/**
 *
 * @author malek
 */
public class test {
     public static void main(String[] args) {
        // test DB Connexion
        db cnx = db.getInstance();
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
        evenementsService es= new evenementsService();
        evenements e = new evenements("testmodif",20,"10/02/2020","Etude","Salle_publique","Dev");
                evenements e3 = new evenements(1);
        evenements e2 = new evenements("malek",23,"18/04/2022","Etude","Salle_publique","Sport");
       //System.out.println(es.afficher());
        es.modifier(e);
//       es.ajouter(e);
//       es.ajouter(e2);
//        System.out.println(es.afficher());
//   System.out.println(e.getID_Event());
//      es.supprimer(e2);
        System.out.println(es.afficher());

    }
}
