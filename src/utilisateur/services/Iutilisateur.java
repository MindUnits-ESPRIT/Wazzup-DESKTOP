/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.services;
import java.util.List;
import utilisateur.entities.*;

/**
 *
 * @author malek
 */
public interface Iutilisateur<T> {
    void ajouter(T entity);
    void ajouter_interet(int i,String payload);
    void modifier (int i, T entity,int modif);
    void supprimer (int i);
    void Get_Collaborations_list(int i);
    List<T> afficher();
}
