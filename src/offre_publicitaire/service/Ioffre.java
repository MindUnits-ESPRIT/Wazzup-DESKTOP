package offre_publicitaire.service;
import java.util.List;

/**
 *
 * @author ahmed
 */
public interface Ioffre<O> {
    void ajouter(O entity);
  void modifier (int i, O entity);
   void supprimer (int i);
    List<O> afficher();
}
