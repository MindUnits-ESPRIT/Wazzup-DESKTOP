/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rencontre.services;

import Rencontre.entities.Rencontre;
import evenements.URL.URL;
import evenements.entities.evenements;
import java.util.List;

/**
 *
 * @author SRN
 */
public interface IRencontre <T>{
     void ajouter(Rencontre r,evenements e);
    void modifier (int id, String tr);
    void supprimer (int id);
    List<T> afficher(int id);
}
