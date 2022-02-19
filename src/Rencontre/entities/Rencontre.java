/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rencontre.entities;

/**
 *
 * @author SRN
 */
public class Rencontre {
    private int ID_Ren;
    private String Type_Rencontre;
    private String URL_Invitation;

    public Rencontre() {
    }

    public Rencontre(int ID_Ren, String Type_Rencontre, String URL_Invitation) {
        this.ID_Ren = ID_Ren;
        this.Type_Rencontre = Type_Rencontre;
        this.URL_Invitation = URL_Invitation;
    }

    public Rencontre(String Type_Rencontre, String URL_Invitation) {
        this.Type_Rencontre = Type_Rencontre;
        this.URL_Invitation = URL_Invitation;
    }

    public int getID_Ren() {
        return ID_Ren;
    }

    public void setID_Ren(int ID_Ren) {
        this.ID_Ren = ID_Ren;
    }

    public String getType_Rencontre() {
        return Type_Rencontre;
    }

    public void setType_Rencontre(String Type_Rencontre) {
        this.Type_Rencontre = Type_Rencontre;
    }

    public String getURL_Invitation() {
        return URL_Invitation;
    }

    public void setURL_Invitation(String URL_Invitation) {
        this.URL_Invitation = URL_Invitation;
    }

    @Override
    public String toString() {
        return "Rencontre{" + "ID_Ren=" + ID_Ren + ", Type_Rencontre=" + Type_Rencontre + ", URL_Invitation=" + URL_Invitation + '}';
    }
    
}
