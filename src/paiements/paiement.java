/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paiements;

import paiement.entities.*;
import paiement.service.*;
import database.db ;
/**
 *
 * @author remo
 */
public class paiement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PaiementService ps = new PaiementService();
        Paiement p = new paiement("aaaaa","aaaa","aaaa");
//        ps.ajouter2(p);

        System.out.println(ps.ajouter());
        
    }
    
}
