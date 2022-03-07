package offre_publicitaire.service;
import java.util.List;
import utilisateur.entities.utilisateur;

/**
 *
 * @author ahmed
 */
public interface Ioffre<O> {
    void ajouter(O entity,utilisateur U);
  void modifier (int i, O entity);
   void supprimer (int i);
    List<O> afficher();
}
