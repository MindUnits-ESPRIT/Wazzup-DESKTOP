/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.entities;
/**
 *
 * @author Misow3002
 */
public class publication {
    private int ID_publication ;
    private int ID_utilisateur ;
    private String Description ;
    private String Fichier ;
    private String Date;

    public publication(int ID_publication, int ID_utilisateur, String Description, String Fichier,String Date) {
        this.ID_publication = ID_publication;
        this.ID_utilisateur = ID_utilisateur;
        this.Description = Description;
        this.Fichier = Fichier;
        this.Date=Date;
    }
        public publication(String Description, String Fichier) {
        this.Description = Description;
        this.Fichier = Fichier;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public publication(int ID_publication) {
        this.ID_publication = ID_publication;
    }

    public publication() {
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getFichier() {
        return Fichier;
    }

    public void setFichier(String Fichier) {
        this.Fichier = Fichier;
    }

    @Override
    public String toString() {
        return "Publication : {" + "ID_publication=" + ID_publication + ", ID_utilisateur=" + ID_utilisateur + ", Description=" + Description + ", Fichier=" + Fichier +", Date ="+Date +"} \n";
    }
}
