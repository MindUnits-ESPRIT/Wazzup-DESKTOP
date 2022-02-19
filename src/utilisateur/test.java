/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur;
import utilisateur.services.*;
import utilisateur.entities.*;
import database.db;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject; 

/**
 *
 * @author malek
 */
public class test{
    public static void main(String[] args) {
        // test DB Connexion
//        db cnx = db.getInstance();
//        System.out.println(cnx.hashCode());
//                // Test du table utilisateur
        UtilisateurService us= new UtilisateurService();
//        utilisateur u = new utilisateur("Mahdi","GRRRR",22,24666800,"test.user@esprit.tn","hellotest","User",4,"Male");
//        us.ajouter(u);
 System.out.println(us.afficher());
        db cnx = db.getInstance();
        // Verifier que l'instance de connexion de base de donnée est unique 
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
//        UtilisateurService us= new UtilisateurService();
        utilisateur u = new utilisateur("pidev","test",22,24664800,"malek.abbes@esprit.tn","hellotest","User",4,"Male");
                                        /// CRUD ///
//        Test de modification
//        utilisateur u1 = new utilisateur("malek","abbes",23,24664880,"malek.abbes@esprit.tn","testing modif","User",4,"Male");
//        us.modifier(20, u);

//       Test de l'ajout
//us.ajouter(u);

//       Test de suppression
        us.supprimer(20);
 
                  // L'affichage
        us.Get_Collaborations_list(3);
//        System.out.println(us.afficher());
    }
}
