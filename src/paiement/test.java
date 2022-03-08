/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paiement;

import paiement.entities.*;
import paiement.service.*;
import database.db ;
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
        PaiementService ps = new PaiementService();
        paiement p = new paiement("Stripe",10);
        paiement p3 = new paiement("Credit_Card",11);
       // ps.ajouter(p);
        //ps.supprimer(2);
        //ps.modifier(3, p2);
      //System.out.println(ps.afficher());
     System.out.println(ps.remplirArea());
    }
    
}
