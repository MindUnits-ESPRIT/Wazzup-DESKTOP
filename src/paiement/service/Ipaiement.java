package paiement.service;
import java.util.List;

/**
 *
 * @author ahmed
 */
public interface Ipaiement<P> {
    void ajouter(P entity);
    void modifier (int i, P entity);
    void supprimer (int i);
    List<P> afficher();
}