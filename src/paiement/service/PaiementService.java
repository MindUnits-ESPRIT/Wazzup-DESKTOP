/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import paiement.entities.paiement;
import database.db;
import paiement.entities.paiement;
import paiement.service.PaiementService;

public class PaiementService implements Ipaiement<paiement> {
    private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    
    public PaiementService() {
        conn= db.getInstance().getCnx();
    }

    @Override
    public void ajouter(paiement p) {
      String req = "INSERT INTO `paiement` (`Methode_paiement`,`prix`) VALUES (?,?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setString(1, p.getType_p());
          pste.setFloat(2, p.getPrix());
          pste.executeUpdate();
          System.out.println("Paiement creé");
      } catch(SQLException ex){
          Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Paiement non creé "+ ex);
      }
    }
    
        @Override
    public List<paiement> afficher() {
          List<paiement> paiements = new ArrayList<>();
        String req = "SELECT * FROM `paiement`";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while(rs.next()){
                paiement p = new paiement();
                p.setID_Paiement(rs.getInt("ID_Paiement"));
                p.setDate_paiement(rs.getString(2));
                p.setType_p(rs.getString(3));
                p.setPrix(rs.getFloat(4));
                paiements.add(p);
            }
            } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paiements;
    }

        @Override
    public void modifier(int i,paiement u) {
        System.out.println(u.getID_Paiement());
        String req="UPDATE `paiement` SET `Methode_paiement`=? `prix` WHERE `ID_Paiement`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,u.getType_p());
            pste.setFloat(1,u.getPrix());
            pste.executeUpdate();
            System.out.println("Paiemetn bien modifié");
        } catch (SQLException ex) {
         System.out.println("Paiement n'a pas été modifié");
         Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int i) {
     String req = "DELETE FROM `paiement` WHERE `ID_Paiement` = '"+i+"' ";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Paiement supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Paiement non supprimé "+ ex);
        }
    }



    
}
