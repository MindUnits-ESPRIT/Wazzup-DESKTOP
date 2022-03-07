package facture.services;
import java.util.List;
import offre_publicitaire.entities.offre_publicitaire;
import paiement.entities.paiement;
import utilisateur.entities.utilisateur;

/**
 *
 * @author ahmed
 */
public interface Ifacture<F> {
    void ajouter(F entity,utilisateur U,offre_publicitaire o,paiement p);
   void modifier (int i, F entity);
   void supprimer (int i);
    List<F> afficher();
    List<String> remplirdate();
}
