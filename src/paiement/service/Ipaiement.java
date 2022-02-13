package paiement.service;
import java.util.List;

/**
 *
 * @author malek
 */
public interface Ipaiement<P> {
    void ajouter(P entity);
    void modifier (P entity);
    void supprimer (P entity);
    List<P> afficher();
}