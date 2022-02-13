/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements.services;

import java.util.List;

/**
 *
 * @author SRN
 */
public interface Ievenement<T> {
     void ajouter(T entity);
    void modifier (T entity);
    void supprimer (T entity);
    List<T> afficher();
}