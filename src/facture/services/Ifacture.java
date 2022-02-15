package facture.services;
import java.util.List;

/**
 *
 * @author ahmed
 */
public interface Ifacture<F> {
    void ajouter(F entity);
   void modifier (int i, F entity);
   void supprimer (int i);
    List<F> afficher();
}
