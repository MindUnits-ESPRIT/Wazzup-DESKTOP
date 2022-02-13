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

public class PaiementService implements Ipaiement<paiement> {
    private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    
    public PaiementService() {
        conn= db.getInstance().getCnx();
    }

    @Override
    public void ajouter(paiement p) {
      String req = "INSERT INTO `paiement` (`Methode_paiement`) VALUES (?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setString(1, p.getType_p());
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
                p.setID_Paiment(rs.getInt("ID_Paiement"));
                p.setType_p(rs.getString(3));
                paiements.add(p);
            }
            } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paiements;
    }

    @Override
    public void modifier(paiement entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(paiement entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    
}
