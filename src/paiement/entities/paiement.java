/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.entities;

import java.sql.Date;

/**
 *
 * @author SBS
 */
public class paiement {
    
    private int ID_Paiement;
    private String date_paiement;
    private String Type_p;

    public paiement(int ID_Paiement, String date_paiement, String Type_p) {
        this.ID_Paiement = ID_Paiement;
        this.date_paiement = date_paiement;
        this.Type_p = Type_p;
    }

    
    public paiement(String date_paiement, String Type_p) {
        this.date_paiement = date_paiement;
        this.Type_p = Type_p;
    }

    public paiement() {
    }

    

    public int getID_Paiment() {
        return ID_Paiement;
    }

    public void setID_Paiment(int ID_Paiment) {
        this.ID_Paiement = ID_Paiment;
    }

    public String getDate_paiment() {
        return date_paiement;
    }

    public void setDate_paiment(String date_paiment) {
        this.date_paiement = date_paiment;
    }

    public String getType_p() {
        return Type_p;
    }

    public void setType_p(String Type_p) {
        this.Type_p = Type_p;
    }

    @Override
    public String toString() {
        return "paiement{" + "ID_Paiement=" + ID_Paiement + ", date_paiement=" + date_paiement + ", Type_p=" + Type_p + '}';
    }

   
    
    
    
    
    
    
    
    
}
