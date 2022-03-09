/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rencontre;

import Rencontre.entities.Rencontre;
import Rencontre.services.RencontreService;
import database.db;
import evenements.URL.URL;
import evenements.entities.evenements;


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
                RencontreService rs = new RencontreService();

                evenements e =new evenements(33);
                URL u = new URL();
                Rencontre r = new Rencontre("Virtuel", u.GetUrl_Rencontre(5));
                rs.ajouter(r,e);
               // System.out.println(u.GetUrl_Rencontre(5));
//                rs.modifier(35, "Virtuel");
                //rs.supprimer(01);
               // rs.afficher(02);

                //Rencontre r = new Rencontre(01,"Vie_Reel", "http://example.com/01");
                //rs.ajouter(r);
                //rs.modifier(20, "Virtuel","http://example.com/20");
               // rs.supprimer(01);
                rs.afficher(33);
    }
}
