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
import org.json.JSONObject;
import org.json.simple.JSONArray;

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
                RencontreService rs = new RencontreService();
                evenements e =new evenements(33);
                URL u = new URL();
                Rencontre r = new Rencontre("Vie_Reel", u.GetUrl_Rencontre(5));
                rs.ajouter(r,e);
                System.out.println(u.GetUrl_Rencontre(5));
                rs.modifier(35, "Virtuel");
                //rs.supprimer(01);
               // rs.afficher(02);
    }
}
