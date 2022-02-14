package collab.services;
import java.util.List;
import collab.entities.*;
/**
 *
 * @author mouhi
 */

public interface ICollab<T> {
    void creer (T entity);
    void modifier (int id, String nom);
    void supprimer (int id);
    List<T> afficher(int id);
    
}
