package collab.services;
import java.util.List;
import collab.entities.*;
/**
 *
 * @author mouhib
 */

public interface ICollab<T> {
    void creer (T entity , int id_user);
    void modifier (int id, String nom);
    void supprimer (int id);
    List<T> afficher(int id);
    
}
