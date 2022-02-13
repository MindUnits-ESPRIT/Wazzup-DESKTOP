/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur;
import utilisateur.services.*;
import utilisateur.entities.*;
import database.db;

/**
 *
 * @author malek
 */
public class test{
    public static void main(String[] args) {
        // test DB Connexion
        db cnx = db.getInstance();
        System.out.println(cnx.hashCode());
                // Test du table utilisateur
        UtilisateurService us= new UtilisateurService();
        utilisateur u = new utilisateur("test","user",22,24666800,"test.user@esprit.tn","hellotest","User",4);
        us.ajouter(u);
        System.out.println(us.afficher());
    }
}
