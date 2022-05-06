/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package offre_publicitaire;

import offre_publicitaire.entities.*;
import offre_publicitaire.service.*;
import database.db ;
import utilisateur.entities.utilisateur;
/**
 *
 * @author ahmed
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        db cnx = db.getInstance();
        System.out.println(cnx.hashCode());
        OffreService os = new OffreService();
        offre_publicitaire o = new offre_publicitaire("java","test",4);
        utilisateur u1 = new utilisateur(58,"malek","abbes");
       // offre_publicitaire o2 = new offre_publicitaire("wiki","pc",4);
        os.ajouter(o,u1);
        //os.supprimer(2);
        //os.modifier(1,o2);
      System.out.println(os.afficher());
    }
    
}
