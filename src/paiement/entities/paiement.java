/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.entities;

import java.sql.*;

/**
 *
 * @author ahmed
 */
public class paiement {
    
    private int ID_Paiement;
    private String date_paiement;
    private String Type_p;
    private float prix;

    public paiement(int ID_Paiement, String date_paiement, String Type_p, float prix) {
        this.ID_Paiement = ID_Paiement;
        this.date_paiement = date_paiement;
        this.Type_p = Type_p;
        this.prix = prix;
    }

    public paiement(String Type_p, float prix) {
        this.Type_p = Type_p;
        this.prix = prix;
    }

    public paiement() {
    }

    public int getID_Paiement() {
        return ID_Paiement;
    }

    public void setID_Paiement(int ID_Paiement) {
        this.ID_Paiement = ID_Paiement;
    }

    public String getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(String date_paiement) {
        this.date_paiement = date_paiement;
    }

    public String getType_p() {
        return Type_p;
    }

    public void setType_p(String Type_p) {
        this.Type_p = Type_p;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "paiement{" + "ID_Paiement=" + ID_Paiement + ", date_paiement=" + date_paiement + ", Type_p=" + Type_p + ", prix=" + prix + '}';
    }
}
