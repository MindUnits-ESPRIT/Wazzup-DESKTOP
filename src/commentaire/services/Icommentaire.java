/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentaire.services;
import java.util.List;
import publication.entities.publication;
import utilisateur.entities.utilisateur;

/**
 *
 * @author Misow3002
 * @param <commentaire>
 */
public interface Icommentaire<commentaire> {
    void Creer_C(publication P,commentaire C,utilisateur U);
    void Modifier_C (publication P,commentaire C);
    void Supprimer_C (publication P,commentaire C);
    List<commentaire> Afficher_C();
    List<commentaire> Afficher_CById(int id);
}
