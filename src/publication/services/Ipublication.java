/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.services;

import java.util.List;
import utilisateur.entities.utilisateur;

/**
 *
 * @author Misow3002
 * @param <publication>
 */
public interface Ipublication<publication> {
    void Creer_P(publication P,utilisateur U);
    void Modifier_P (publication P,utilisateur U);
    void Supprimer_P (publication P,utilisateur U);
    void Signaler_P (publication P,utilisateur U,int Type);
    List<publication> Afficher_P();
    List<publication> Afficher_P(utilisateur U);
    
}
