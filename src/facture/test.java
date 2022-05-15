/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package facture;

import facture.entities.*;
import facture.services.*;
import database.db ;
import offre_publicitaire.entities.offre_publicitaire;
import paiement.entities.paiement;
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
//        db cnx = db.getInstance();
 //       System.out.println(cnx.hashCode());
        factureService fs = new factureService();
        facture f = new facture(".PDF");
        facture f2 = new facture(".docx");
//        paiement p1 = new paiement("Stripe",10);

        paiement p1 = new paiement(41);
      //  offre_publicitaire o = new offre_publicitaire("java","test",4);
        offre_publicitaire o = new offre_publicitaire(10);
        utilisateur u1 = new utilisateur(58,"malek","abbes");
           // fs.ajouter(f,u1,o,p1);

            //paiement p1 = new paiement(31);
//        offre_publicitaire o = new offre_publicitaire("java","test",4);
      //  offre_publicitaire o = new offre_publicitaire(9);
       // utilisateur u1 = new utilisateur(19,"spx","tn");
        fs.ajouter(f,u1,o,p1);
        //fs.supprimer(3);
        //fs.modifier(2,f);
      //System.out.println(fs.afficher());
    }
    
}
