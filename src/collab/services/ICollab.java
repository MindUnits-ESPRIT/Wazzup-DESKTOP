package collab.services;
import java.util.List;
import collab.entities.*;
import javafx.collections.ObservableList;
import utilisateur.entities.utilisateur;
/**
 *
 * @author mouhib
 */

public interface ICollab<T> {
    int creer (T entity , int id_user);
    void modifier (String nom , String nnom);
    void supprimer (String nom );
    ObservableList<utilisateur> afficherCollab_Membres (int id );
    ObservableList<T> afficher(int id);
    int ajouter_membre(int idc, int id);
    void supprimer_membre(int idm , int id);
    Salle_Collaboration getSalleInfo (String nom);
    utilisateur getinfo (int idu);
    
}
