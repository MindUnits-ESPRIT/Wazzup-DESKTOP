/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalleCinema.services;

import SalleCinema.entities.SalleCinema;
import database.db;
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
public class SalleCinemaService implements ISalleCinema<SalleCinema>{
    private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    public SalleCinemaService() {
        conn= db.getInstance().getCnx();
    }

    
    @Override
    public void ajouter(SalleCinema s, evenements e) {
        String req = "INSERT INTO `salle_cinema` (`ID_Salle`,`Nom_Salle`,`URL_Film`,`URL_Salle`,`Chat`,`ID_Event`) VALUES ('"+s.getID_Salle()+"','" + s.getNomSalle() + "','" + s.getURL_Film() + "','" + s.getURL_Salle()+ "','" + s.getChat() + "','"+e.getID_Event()+"')";
      try {
          pste = conn.prepareStatement(req);
          pste.executeUpdate();
          System.out.println("SalleCinema creé");
      } catch(SQLException ex){
          Logger.getLogger(SalleCinemaService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("SalleCinema non creé "+ ex);
      }
    }
    
@Override
    public void modifier(int id, String NomS,String NomF) {
        String URLF = "http://example.com/"+ NomF;
         String URLS = "http://example.com/"+ NomS;
    
           String req = "UPDATE `salle_cinema` SET `Nom_Salle`='"+NomS+"' , `URL_Film`='"+URLF+"', `URL_Salle`='"+URLS+"'  WHERE `ID_Salle`='"+id+"'  ";
        try {
        pste = conn.prepareStatement(req);
              pste.executeUpdate();
               System.out.println("SalleCinema modifiee");
               System.out.println("Nouveau Salle : "+NomS+"Film"+ NomF + " URL Film: "+URLF+ " URL Salle: "+URLS);
    } catch (SQLException ex) {
        Logger.getLogger(SalleCinemaService.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("SalleCinema non modifiee "+ ex);
    }
    }
    
    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `salle_cinema` WHERE `ID_Salle` = '"+id+"' ";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate(req);
            System.out.println("SalleCinema supprimee avec success");


        } catch (SQLException ex) {
            Logger.getLogger(SalleCinemaService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("SalleCinema non supprimee "+ ex);
        }
    }

    @Override
    public ObservableList<SalleCinema> afficher(int id) {
         ObservableList<SalleCinema> SalleCinema =FXCollections.observableArrayList();
         String req = "SELECT * FROM `salle_cinema` where `ID_Salle` = ? ";
          try {
          pste = conn.prepareStatement(req);
          pste.setInt(1, id);
          ResultSet rs = pste.executeQuery();
          System.out.println("SalleCinema creer par l'utilisateur du id "+id+" sont:");
          while(rs.next()){
                SalleCinema s = new SalleCinema();
                s.setID_Salle(rs.getInt("ID_Salle"));
                s.setNomSalle(rs.getString(2));
                s.setURL_Salle(rs.getString(3));
                s.setURL_Film(rs.getString(4));
                SalleCinema.add(s);
                System.out.println(s.toString());
            }
      } catch(SQLException ex){
          Logger.getLogger(SalleCinemaService.class.getName()).log(Level.SEVERE,null,ex);
      }
          return (SalleCinema);
    }
    
}
