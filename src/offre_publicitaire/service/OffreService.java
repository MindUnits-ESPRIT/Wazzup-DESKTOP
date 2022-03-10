/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre_publicitaire.service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import offre_publicitaire.entities.offre_publicitaire;
import utilisateur.entities.utilisateur;
import database.db;
import javafx.fxml.FXML;
import paiement.GUI.FactureFXMLController;


/**
 *
 * @author malek
 */
public class OffreService implements Ioffre<offre_publicitaire> {
    private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    
    public OffreService() {
        // Creation d'une instance de base de données 
        conn= db.getInstance().getCnx();
    }
         // Method CRUD#1 : Ajouter
    @Override
    public void ajouter(offre_publicitaire u,utilisateur U) {
      String req = "INSERT INTO `offre_publicitaire` (`ID_Utilisateur`,`nom_offre`,`contenu_offre`,`nbr_max_offre`) VALUES (?,?,?,?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setInt(1,U.getID_Utilisateur());
          pste.setString(2, u.getNom_offre());
          pste.setString(3, u.getContenu_offre());
          pste.setFloat(4, u.getNbr_max_offre());
          pste.executeUpdate();
          System.out.println("Offre creé");
      } catch(SQLException ex){
          Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Offre non creé "+ ex);
      }
    }
     
         // Method : Affichage
    @Override
    public List<offre_publicitaire> afficher() {
        List<offre_publicitaire> offre_publicitaire = new ArrayList<>();
        String req = "SELECT * FROM `offre_publicitaire`";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            
            while(rs.next()){
                
                offre_publicitaire u = new offre_publicitaire();
                u.setId_offre(rs.getInt("id_offre"));
                u.setNom_offre(rs.getString(2));
                u.setContenu_offre(rs.getString(3));
                u.setNbr_max_offre(rs.getInt(4));
          
                offre_publicitaire.add(u);
            }
            } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offre_publicitaire;
    }
    

         // Method CRUD#2: Modification
    @Override
    public void modifier(int i,offre_publicitaire u) {
        System.out.println(u.getId_offre());
        String req="UPDATE `offre_publicitaire` SET `nom_offre`=? , `contenu_offre`=? ,`nbr_max_offre`=? WHERE `id_offre`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
           pste.setString(1, u.getNom_offre());
           pste.setString(2, u.getContenu_offre());
           pste.setFloat(3, u.getNbr_max_offre());
           pste.executeUpdate();
            System.out.println("Utilisateur bien modifié");
        } catch (SQLException ex) {
         System.out.println("Utilisateur n'a pas été modifié");
         Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        // Method CRUD#3: Suppression
    @Override

        public void supprimer(int i) {
               String req = "DELETE FROM `offre_publicitaire` WHERE `id_offre`= '"+i+"' ";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Offre supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Utilisateur non supprimé "+ ex);
        }
    }
       
    
}
