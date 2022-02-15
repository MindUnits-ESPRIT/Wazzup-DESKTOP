/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentaire;

import commentaire.entities.commentaire;
import publication.entities.publication;
import commentaire.services.commentaireService;
import utilisateur.entities.utilisateur;

/**
 *
 * @author Misow3002
 */
public class test {
public static void main(String[] args) {
    
    commentaireService CS = new commentaireService();
//---------- BEGIN CREER COMMENTAIRE -------
//    publication P = new publication(1);
//    utilisateur U = new utilisateur(2,"malek","abbes");
//    commentaire C = new commentaire();
//    CS.Creer_C(P,C,U);
//---------- END CREER COMMENTAIRE -------

//---------- BEGIN SUPPRIMER COMMENTAIRE -------
//    publication P = new publication(1);
//    commentaire C = new commentaire(4,1,2);
//    CS.Supprimer_C(P,C);
//---------- END SUPPRIMER COMMENTAIRE -------
//---------- BEGIN MODIFIER COMMENTAIRE -------
    publication P = new publication(1);
    commentaire C = new commentaire(5,1,2);
    CS.Modifier_C(P,C);
//---------- END MODIFIER COMMENTAIRE -------
//AFFICHAGE SELECT
    System.out.println(CS.Afficher_C());
  }
}
