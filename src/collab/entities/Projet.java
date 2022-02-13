/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collab.entities;

/**
 *
 * @author mouhi
 */
public class Projet {
    
    private int ID ;
    private int ID_Collab ;
    private String Nom_Projet ;
    private String description ;
    private String URL_Trello ;  

    

    

    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom_Projet() {
        return Nom_Projet;
    }

    public void setNom_Projet(String Nom_Projet) {
        this.Nom_Projet = Nom_Projet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL_Trello() {
        return URL_Trello;
    }

    public void setURL_Trello(String URL_Trello) {
        this.URL_Trello = URL_Trello;
    }

    public int getID_Collab() {
        return ID_Collab;
    }

    public void setID_Collab(int ID_Collab) {
        this.ID_Collab = ID_Collab;
    }
    

    @Override
    public String toString() {
        return "Projet{" + "ID=" + ID + ", Nom_Projet=" + Nom_Projet + ", description=" + description + ", URL_Trello=" + URL_Trello + '}';
    }

    public Projet() {
    }

    public Projet(int ID_Collab, String Nom_Projet, String description, String URL_Trello) {
        this.ID_Collab = ID_Collab;
        this.Nom_Projet = Nom_Projet;
        this.description = description;
        this.URL_Trello = URL_Trello;
    }

 

    public Projet(String Nom_Projet, String description, String URL_Trello) {
        this.Nom_Projet = Nom_Projet;
        this.description = description;
        this.URL_Trello = URL_Trello;
    }
    
    
    
}
