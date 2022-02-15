package collab.services;
import java.util.List;
import collab.entities.*;
/**
 *
 * @author mouhib
 */

public interface IProjet <T> {
     void creer (T entity);
     void modifier (int id, String nom, String desc);
     void supprimer (int id);
     List<T> afficher(int id);
}
