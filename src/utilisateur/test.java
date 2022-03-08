/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur;
import utilisateur.services.*;
import utilisateur.entities.*;
import database.db;
import java.util.ArrayList;
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
// System.out.println(us.afficher());
        db cnx = db.getInstance();
        // Verifier que l'instance de connexion de base de donnée est unique 
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
//        UtilisateurService us= new UtilisateurService();
        utilisateur u = new utilisateur("mouhib","salah","17/02/1999","24664800","malekabbes@esprit.tn","malek123","User",4,"Male");
                                        /// CRUD ///
//        Test de modification
//        utilisateur u1 = new utilisateur("malek","abbes",23,24664880,"malek.abbes@esprit.tn","testing modif","User",4,"Male");
//        us.modifier(20, u);

//       Test de l'ajout
//us.ajouter(u);

//       Test de suppression
//       us.supprimer(20);
 
//        L'affichage
//        us.Get_Collaborations_list(3);
//        System.out.println(us.afficher());

//        L'ajout d'un interet a l'utilisateur
us.ajouter_interet(58, "Sport");

           // AUTHENTIFICATION TEST
//           utilisateur auth = new utilisateur("malek.abbes@esprit.tn","testspx1");
//           System.out.println(us.auth(auth));
//           
//           String email="malek.abbes@yahoo.com";
//           String email2="malekabbes@esprit.tn";
//       
//System.out.println(us.isEmailExist(email2));
//        System.out.println(us.ActivatedCheck("malekabbes@esprit.tn"));
//  utilisateur u2 = new utilisateur(24,"aa","bbb","12/12/1999","25555887","malekaa@yahoo.fr","imagelink","11441144","User",2,"Male","15624141114",3,true,false,false,19/12/2022);
//us.ajouter(u2);
//System.out.println(us.checkPassword("aaaAAA11."));
//        System.out.println(us.PictureCheck(58));
//        

System.out.println(us.getAllInterets(58));
    }
    
}
