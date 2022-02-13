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

public abstract class PaiementService implements Ipaiement<paiement> {
    private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    
    public PaiementService() {
        conn= db.getInstance().getCnx();
    }

    @Override
    public void ajouter(paiement p) {
      String req = "INSERT INTO `paiement` (`Date_paiement`,`Methode_paiement`) VALUES (?,?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setString(1, p.getDate_paiment());
          pste.setString(2, p.getType_p());
          pste.executeUpdate();
          System.out.println("Paiement creé");
      } catch(SQLException ex){
          Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Paiement non creé "+ ex);
      }
    }

    
}
