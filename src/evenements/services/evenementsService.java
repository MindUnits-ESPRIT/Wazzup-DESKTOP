/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements.services;

import database.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import evenements.entities.evenements;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import utilisateur.services.UtilisateurService;
/**
 *
 * @author malek
 */
public class evenementsService implements Ievenement<evenements> {
private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    public evenementsService() {
        conn= db.getInstance().getCnx();
    }
    
@Override
    public void ajouter(evenements e) {
String req = "INSERT INTO `evenement` (`ID_Utilisateur`,`Nom_Event`,`Nbr_participants`,`Date_Event`,`Type_Event`,`Event_Visibilite`,`Description`) VALUES ('"+e.getID_Utilisateur()+"','"+e.getNom_Event()+"','"+e.getNbr_participants()+"','"+e.getDate_Event()+"','"+e.getType_Event()+"','"+e.getEvent_Visibilite()+"','"+e.getDescription()+"')";
      try {
          System.out.println("test"+e.toString());
          pste = conn.prepareStatement(req);
          pste.executeUpdate();
          System.out.println("Evenement creé");
      } catch(SQLException ex){
          Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Evenement non creé "+ ex);
      }
    }    

    
@Override
    public void modifier(int id, String nom,int Nbr,String date,String type,String vis,String des) {
       String req1 = "UPDATE `evenement` SET Nom_Event='"+nom+"',`Nbr_participants`='"+Nbr+"',`Date_Event`='"+date+"',`Type_Event`='"+type+"',`Event_Visibilite`='"+vis+"',`Description`='"+des+"'WHERE `ID_Event` = '"+id+"'";
        try {
        pste = conn.prepareStatement(req1);
              pste.executeUpdate();
               System.out.println("Evenement modifie");
    } catch (SQLException ex) {
        Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Evenement non modifie "+ ex);
    }
           
    }

@Override
    public void supprimer(int id) {
        String req3 = "DELETE FROM `evenement` WHERE `ID_Event` = '"+id+"'";
    try {
        pste = conn.prepareStatement(req3);
      
         pste.executeUpdate();
        System.out.println("Evenement supprime");
    } catch (SQLException ex) {
        Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
    System.out.println("Evenement non supprime");
    }

    }

    @Override
    public ObservableList<evenements> afficher(int id) {
        ObservableList<evenements> evenements =FXCollections.observableArrayList();
        String req = "SELECT * FROM `evenement` WHERE `ID_Utilisateur` ='"+id+"'";
  
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while(rs.next()){
                evenements e = new evenements();
                e.setID_Event(rs.getInt("ID_Event"));
                e.setNom_Event(rs.getString(2));
                e.setNbr_participants(rs.getInt(3));
                e.setDate_Event(rs.getString(4));
                e.setType_Event(rs.getString(5));
                e.setEvent_Visibilite(rs.getString(6));
                e.setDescription(rs.getString(7));
                e.setID_Utilisateur(rs.getInt(8));
                e.setDate_P(rs.getString(9));
                evenements.add(e);
                System.out.println(e.toString());
            }
            } catch (SQLException ex) {
            Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }
    
}
