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
String req = "INSERT INTO `evenement` (`Nom_Event`,`Nbr_participants`,`Date_Event`,`Type_Event`,`Event_Visibilite`,`Description`) VALUES (?,?,?,?,?,?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setString(1,e.getNom_Event());
          pste.setInt(2,e.getNbr_participants());
           pste.setString(3,e.getDate_Event());
           pste.setString(4,e.getType_Event());
           pste.setString(5,e.getEvent_Visibilite());
           pste.setString(6,e.getDescription());
          pste.executeUpdate();

          System.out.println("Evenement creé");
      } catch(SQLException ex){
          Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Evenement non creé "+ ex);
      }
    }    

    
@Override
    public void modifier(evenements e) {
       String req1 = "UPDATE `evenement` SET Nom_Event=?,`Nbr_participants`=?,`Date_Event`=?,`Type_Event`=?,`Event_Visibilite`=?,`Description`=?";
    req1 += "WHERE `ID_Event` = ?";
        try {
        pste = conn.prepareStatement(req1);
         pste.setString(1,e.getNom_Event());
          pste.setInt(2,e.getNbr_participants());
           pste.setString(3,e.getDate_Event());
            pste.setString(4,e.getType_Event());
             pste.setString(5,e.getEvent_Visibilite());
              pste.setString(6,e.getDescription());
              pste.setInt(7,4);
              pste.executeUpdate();

               System.out.println("Evenement modifie");
    } catch (SQLException ex) {
        Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Evenement non modifie "+ ex);
    }
           
    }

@Override
 public void supprimer(evenements e) {
    System.out.println("TEST "+ e.getID_Event()); 
    String req= "DELETE FROM evenement WHERE ID_Event = "+e.getID_Event();
try{
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Client supprime");
        } catch (SQLException ex) {
            Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Client NON supprime");
        }
}

//    public void supprimer(evenements e) {
//        // Reqeuete get ID from db
//        System.out.println(e.getNom_Event());
//        String reqq = "SELECT ID_Event FROM `evenement` WHERE Nom_Event= '"+e.getNom_Event()+"' AND Date_Event= '"+e.getDate_Event()+"'";
//        System.out.println(reqq);
//          try {
//        pste = conn.prepareStatement(reqq);
//        ResultSet rs = pste.executeQuery(reqq);
//        if (rs.next()) {
//    int id = rs.getInt(1);
//    String req3 = "DELETE FROM `evenement` WHERE `ID_Event` = '"+id+"' ";
//        try {
//        pste = conn.prepareStatement(req3);
//         pste.executeUpdate();
//        System.out.println("Evenement supprime");
//    } catch (SQLException ex) {
//        Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
//    System.out.println("Evenement non supprime");
//    }
//    System.out.println("GOT ID"+ id); // display inserted record
//         }
//         } catch (SQLException ex) {
//        Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
//    System.out.println("Evenement non supprime");
//    }
//
//    }

    @Override
    public List<evenements> afficher() {
        List<evenements> evenements = new ArrayList<>();
        String req = "SELECT * FROM `evenement`";
        
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
                evenements.add(e);
            }
            } catch (SQLException ex) {
            Logger.getLogger(evenementsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }
    
}