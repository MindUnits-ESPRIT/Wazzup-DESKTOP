/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre_publicitaire.entities;

/**
 *
 * @author SBS
 */
public class offre_publicitaire {
    
    private int id_offre;
    private String nom_offre;
    private String contenu_offre;
    private int  nbr_max_offre	;

    public offre_publicitaire(int id_offre, String nom_offre, String contenu_offre, int nbr_max_offre) {
        this.id_offre = id_offre;
        this.nom_offre = nom_offre;
        this.contenu_offre = contenu_offre;
        this.nbr_max_offre = nbr_max_offre;
    }

    public offre_publicitaire(String nom_offre, String contenu_offre, int nbr_max_offre) {
        this.nom_offre = nom_offre;
        this.contenu_offre = contenu_offre;
        this.nbr_max_offre = nbr_max_offre;
    }

    public offre_publicitaire() {
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public String getContenu_offre() {
        return contenu_offre;
    }

    public void setContenu_offre(String contenu_offre) {
        this.contenu_offre = contenu_offre;
    }

    public float getNbr_max_offre() {
        return nbr_max_offre;
    }

    public void setNbr_max_offre(int nbr_max_offre) {
        this.nbr_max_offre = nbr_max_offre;
    }

    @Override
    public String toString() {
        return "offre_publicitaire{" + "id_offre=" + id_offre + ", nom_offre=" + nom_offre + ", contenu_offre=" + contenu_offre + ", nbr_max_offre=" + nbr_max_offre + '}';
    }
    
    
    
}
