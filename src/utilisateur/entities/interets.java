/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.entities;

/**
 *
 * @author malek
 */
public class interets {
    public String nom_interet;

    public String getNom_interet() {
        return nom_interet;
    }

    public void setNom_interet(String nom_interet) {
        this.nom_interet = nom_interet;
    }

    public interets() {
    }

    public interets(String nom_interet) {
        this.nom_interet = nom_interet;
    }

    @Override
    public String toString() {
        return "interets{" + "nom_interet=" + nom_interet + '}';
    }
    
}
