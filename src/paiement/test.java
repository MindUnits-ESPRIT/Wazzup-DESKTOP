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
 * @author remo
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
        paiement p = new paiement("Stripe");
        ps.ajouter(p);
        System.out.println(ps.afficher());
    }
    
}
