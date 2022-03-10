/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements.services;

import evenements.entities.evenements;
import java.util.List;

/**
 *
 * @author SRN
 */
public interface Ievenement<T> {
     void ajouter(evenements e);
    void modifier (int id, String nom,int Nbr,String date,String type,String vis,String des);
    void supprimer (int id);
    List<T> afficher(int id);
}
