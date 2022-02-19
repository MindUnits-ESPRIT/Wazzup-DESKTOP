/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema.services;

import java.util.List;

/**
 *
 * @author SRN
 */
public interface ISalleCinema<T> {
    void ajouter(T entity);
    void modifier (int id,String Nom,String NomF);
    void supprimer (int id);
    List<T> afficher(int id);
}
