/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rencontre.services;


import Rencontre.entities.Rencontre;
import database.db;
import evenements.URL.URL;
import evenements.entities.evenements;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SRN
 */
public class RencontreService implements IRencontre<Rencontre> {
private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    public RencontreService() {
        conn= db.getInstance().getCnx();
    }
    
@Override
    public void ajouter(Rencontre r,evenements e) {
     String req = "INSERT INTO `rencontre` (`ID_Ren`,`Type_Rencontre`,`URL_Invitation`,`ID_Event`) VALUES ('"+r.getID_Ren()+"','"+r.getType_Rencontre()+"','"+r.getURL_Invitation()+"','"+e.getID_Event()+"')";
      try {
          pste = conn.prepareStatement(req);
          pste.executeUpdate();
          System.out.println("Evenement creé");
      } catch(SQLException ex){
          Logger.getLogger(RencontreService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Evenement non creé "+ ex);
      }
    }
    

    @Override
    public void modifier(int id, String tr) {
String req1 = "UPDATE `rencontre` SET `Type_Rencontre`='"+tr+"' WHERE `ID_Ren` = '"+id+"'";
        try {
        pste = conn.prepareStatement(req1);
              pste.executeUpdate();
               System.out.println("Rencontre modifie");
    } catch (SQLException ex) {
        Logger.getLogger(RencontreService.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Rencontre non modifie "+ ex);
    }
}

    @Override
    public void supprimer(int id) {
         String req3 = "DELETE FROM `rencontre` WHERE `ID_Ren` = '"+id+"' ";
    try {
        pste = conn.prepareStatement(req3);
      
         pste.executeUpdate();
        System.out.println("Rencontre supprime");
    } catch (SQLException ex) {
        Logger.getLogger(RencontreService.class.getName()).log(Level.SEVERE, null, ex);
    System.out.println("Rencontre non supprime");
    }
    }

    @Override
    public ObservableList<Rencontre> afficher(int id) {
         ObservableList<Rencontre> Rencontre =FXCollections.observableArrayList();
        String req = "SELECT * FROM `rencontre` where `ID_Ren` = '"+id+"'"; 
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while(rs.next()){
                Rencontre r = new Rencontre();
                r.setID_Ren(rs.getInt("ID_Ren"));
               r.setType_Rencontre(rs.getString(2));
                r.setURL_Invitation(rs.getString(3));
                Rencontre.add(r);
                System.out.println(r.toString());
            }
            } catch (SQLException ex) {
            Logger.getLogger(RencontreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Rencontre;
    }
    
}
