/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facture.services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import facture.entities.facture;
import database.db;
import facture.entities.facture;
import facture.services.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import offre_publicitaire.entities.offre_publicitaire;
import utilisateur.entities.utilisateur;
import paiement.entities.paiement;


public class factureService implements Ifacture<facture> {
    private Connection conn;
    private ResultSet rs;
    @FXML
    private ComboBox<String> ComboBoxFacture;
    //private Statement ste;
    private PreparedStatement pste;
    
    public factureService() {
        conn= db.getInstance().getCnx();
    }
 
    @Override
    public void ajouter(facture f,utilisateur U,offre_publicitaire o,paiement p) {
      String req = "INSERT INTO `facture` (`file`, `ID_Utilisateur`, `ID_paiement`, `id_offre`) VALUES (?,?,?,?)";
      try {
          System.out.println("1: "+f.getFile()+"     2: "+U.getID_Utilisateur()+"    3: "+p.getID_Paiement()+"     4: "+o.getId_offre());
          pste = conn.prepareStatement(req);
          pste.setString(1, f.getFile());
          pste.setInt(2,U.getID_Utilisateur());
          pste.setInt(3,p.getID_Paiement());
          pste.setInt(4, o.getId_offre());
          System.out.println("FACTURE : "+ f.getFile() +" / "+U.getID_Utilisateur()+" / "+p.getID_Paiement()+" / "+o.getId_offre()+" / ");
          pste.executeUpdate();
          System.out.println("Facture creé");
      } catch(SQLException ex){
          Logger.getLogger(factureService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Facture non creé "+ ex);
      }
    }
    
        @Override
    public List<facture> afficher() {
          List<facture> facture = new ArrayList<>();
        String req = "SELECT * FROM `facture`";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while(rs.next()){
                facture f = new facture();
                f.setNum_fac(rs.getInt("num_fac"));
                f.setDate_fac(rs.getString(2));
                f.setFile(rs.getString(3));
                facture.add(f);
            }
            } catch (SQLException ex) {
            Logger.getLogger(factureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facture;
    }

        @Override
    public void modifier(int i,facture u) {
        System.out.println(u.getNum_fac());
        String req="UPDATE `facture` SET `file`=? WHERE `num_fac`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,u.getFile());
            pste.executeUpdate();
            System.out.println("Facture bien modifié");
        } catch (SQLException ex) {
         System.out.println("Facture n'a pas été modifié");
         Logger.getLogger(factureService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int i) {
     String req = "DELETE FROM `facture` WHERE `num_fac` = '"+i+"' ";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Facture supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(factureService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Facture non supprimé "+ ex);
        }
    }

    @Override
    public List<String> remplirdate() {
         List<String> facture = new ArrayList<>();
               try {
            String sql="SELECT Date_fac FROM `facture`";
            pste=conn.prepareStatement(sql);
            ResultSet rs =pste.executeQuery();
            
            while(rs.next()){
                facture.add(rs.getString("Date_fac"));
                   
            }  } catch (SQLException ex) {
            Logger.getLogger(factureService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
return facture;
    }

  

}

