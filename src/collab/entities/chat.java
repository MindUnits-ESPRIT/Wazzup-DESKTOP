/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collab.entities;

import java.util.Date;

/**
 *
 * @author mouhi
 */
public class chat {
    private int id ;
    private int ID_Uitlisateur;
    private String body ;   
    private Date date ;
    private int ID_Collab ;
    private String nom ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID_Uitlisateur() {
        return ID_Uitlisateur;
    }

    public void setID_Uitlisateur(int ID_Uitlisateur) {
        this.ID_Uitlisateur = ID_Uitlisateur;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getID_Collab() {
        return ID_Collab;
    }

    public void setID_Collab(int ID_Collab) {
        this.ID_Collab = ID_Collab;
    }

    @Override
    public String toString() {
        return "chat{" + "id=" + id + ", ID_Uitlisateur=" + ID_Uitlisateur + ", body=" + body + ", date=" + date + ", ID_Collab=" + ID_Collab + '}';
    }

    public chat() {
    }

    public chat(int ID_Uitlisateur, String body, int ID_Collab) {
        this.ID_Uitlisateur = ID_Uitlisateur;
        this.body = body;
        this.ID_Collab = ID_Collab;
    }
    
    
    
    
    
}
