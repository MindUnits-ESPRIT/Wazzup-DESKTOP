/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication;

import publication.entities.publication;
import publication.services.publicationService;
import utilisateur.entities.utilisateur;

/**
 *
 * @author Misow3002
 */
public class test {
    public static void main(String[] args) {
        publicationService PS = new publicationService();
//---------- BEGIN AJOUTER PUBLICATION -------
//        publication P = new publication("test pub","NULL");
//        utilisateur U = new utilisateur(9,"test","user");
//        PS.Creer_P(P,U);
//---------- END AJOUTER PUBLICATION ------
//---------- BEGIN MODIFIER PUBLICATION -------
//    publication P = new publication(1);
//    utilisateur U = new utilisateur(2,"malek","abbes");
//    PS.Modifier_P(P, U);
//---------- END MODIFIER PUBLICATION ------
//---------- BEGIN SUPPRIMER PUBLICATION -------
//    publication P = new publication(2);
//    utilisateur U = new utilisateur(1,"malek","abbes");
//    PS.Supprimer_P (P, U);
//---------- END SUPPRIMER PUBLICATION ------
//---------- BEGIN SIGNALER PUBLICATION -------
    publication P = new publication(3);
    utilisateur U = new utilisateur(2,"malek","abbes");
    PS.Signaler_P(P, U);
//---------- END SIGNALER PUBLICATION ------
//AFFICHAGE SELECT
       // System.out.println(PS.Afficher_P());
       // System.out.println(PS.Afficher_P(U));
    }
}
