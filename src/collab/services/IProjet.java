/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collab.services;
import java.util.List;
import collab.entities.*;
/**
 *
 * @author mouhi
 */

public interface IProjet <T> {
     void creer (T entity);
     void modifier (T entity);
     void supprimer (T entity);
     List<T> afficher();
}
