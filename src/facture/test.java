/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package facture;

import facture.entities.*;
import facture.services.*;
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
        factureService fs = new factureService();
        facture f = new facture(".PDF");
        facture f2 = new facture(".docx");
        fs.ajouter(f);
        //fs.supprimer(3);
        //fs.modifier(2,f);
      System.out.println(fs.afficher());
    }
    
}
