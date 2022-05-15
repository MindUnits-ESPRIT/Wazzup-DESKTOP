/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentaire.entities;

/**
 *
 * @author Misow3002
 */
public class commentaire {
         private int ID_Commentaire ;
         private int ID_publication ;
         private int ID_utilisateur ;
         private String Message ;
         private String Date;

    public commentaire() {
    }

    public commentaire(int ID_Commentaire, int ID_publication, int ID_utilisateur) {
        this.ID_Commentaire = ID_Commentaire;
        this.ID_publication = ID_publication;
        this.ID_utilisateur = ID_utilisateur;
    }

    public int getID_Commentaire() {
        return ID_Commentaire;
    }

    public void setID_Commentaire(int ID_Commentaire) {
        this.ID_Commentaire = ID_Commentaire;
    }

    public int getID_publication() {
        return ID_publication;
    }

    public void setID_publication(int ID_publication) {
        this.ID_publication = ID_publication;
    }

    public int getID_utilisateur() {
        return ID_utilisateur;
    }

    public void setID_utilisateur(int ID_utilisateur) {
        this.ID_utilisateur = ID_utilisateur;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
    
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }


    @Override
    public String toString() {
        return "commentaire{" + "ID_publication=" + ID_publication + ", ID_utilisateur=" + ID_utilisateur + ", Message=" + Message +" }\n";
    }

    public commentaire(int ID_publication, int ID_utilisateur, String Message) {
        this.ID_publication = ID_publication;
        this.ID_utilisateur = ID_utilisateur;
        this.Message = Message;
    }
         
}
