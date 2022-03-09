/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collab.services;

import collab.entities.Salle_Collaboration;
import javafx.collections.ObservableList;
import utilisateur.entities.utilisateur;

/**
 *
 * @author mouhi
 */
public interface IChat <T>{
    void send (T entity);
    void recive (int id_collab , int id_utilisateur);
    ObservableList<T> afficher(int id_collab);
  
}
