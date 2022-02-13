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
        evenements e = new evenements("test",30,"30/02/2020","Etude","Salle_publique","science");
        es.ajouter(e);
       System.out.println(es.afficher());
        es.modifier(e);
        System.out.println(es.afficher());
        es.supprimer(e);
        System.out.println(es.afficher());
        System.out.println("DELETE FROM evenement WHERE ID_Event = "+ e.getID_Event());

    }
}