/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publication.services;
import database.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import paiement.GUI.FactureFXMLController;
import publication.entities.publication;
import utilisateur.entities.utilisateur;

/**
 *
 * @author Misow3002
 */
public class publicationService implements Ipublication<publication> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    
    public publicationService() {
    conn = db.getInstance().getCnx();
}
 @Override
    public void Creer_P(publication P,utilisateur U) {
        String req="INSERT INTO `publication`(`Id_Utilisateur`,`Description`,`Fichier`) VALUES(?,?,?)";
        try {
            System.out.println("Cc : "+U.getID_Utilisateur());
            pste = conn.prepareStatement(req);
            pste.setInt(1,U.getID_Utilisateur());
            pste.setString(2,P.getDescription());
            pste.setString(3,P.getFichier());
            pste.executeUpdate();
            System.out.println("Publication Publié");
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@Override
    public void Modifier_P(publication P,utilisateur U) {
        String req="UPDATE `publication` SET `Description`=?,`Fichier`=?,`Date_Publication`=CURRENT_TIMESTAMP WHERE `Id_Publication`='"+P.getID_publication()+"' And `Id_Utilisateur`='"+U.getID_Utilisateur()+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,"FIXED 2 Description");
            pste.setString(2,"");
            pste.executeUpdate();
            System.out.println("Publication Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
@Override
    public void Supprimer_P(publication P,utilisateur U) {
          String req="DELETE FROM `publication` WHERE `Id_Publication`='"+P.getID_publication()+"' And `Id_Utilisateur`='"+U.getID_Utilisateur()+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Publication Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
@Override
    public void Signaler_P(publication P,utilisateur U) {
        String Liste_Signal="";
        try {
        String req_fetch = "SELECT * FROM `publication` WHERE `Id_Publication`='"+P.getID_publication()+"' And `Id_Utilisateur`!='"+U.getID_Utilisateur()+"'";
        ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(req_fetch);
            
        while(rs.next()){
         Liste_Signal=rs.getString("Liste_Signal");
         JSONArray Liste_Signal_JSON = new JSONArray(Liste_Signal);
         JSONObject user = new JSONObject();
         user.put("ID","69");
         user.put("Type_ID","0");
         user.put("Description","TeSt Decription");
         Liste_Signal_JSON.put(user);
            System.out.println(Liste_Signal_JSON.toString());
            System.out.println("Nbr : "+Liste_Signal_JSON.length());
         String req="UPDATE `publication` SET `Nbr_Signal`=?,`Liste_Signal`=? WHERE `Id_Publication`='"+P.getID_publication()+"' And `Id_Utilisateur`!='"+U.getID_Utilisateur()+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1,Liste_Signal_JSON.length());
            pste.setString(2,Liste_Signal_JSON.toString());
            pste.executeUpdate();
            System.out.println("Publication Signlé");
        } catch (SQLException ex1) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex1);
        }
        }  
        } catch (SQLException ex2) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex2);
        }
        //System.out.println(Liste_Signal);
        

    }

@Override
    public List Afficher_P() {
        List<publication> publications = new ArrayList<>();
        String req = "SELECT * FROM `publication`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                publication p = new publication();
                p.setID_publication(rs.getInt("Id_Publication"));
                p.setID_utilisateur(rs.getInt("Id_Utilisateur"));
                p.setDescription((rs.getString("Description")));
                p.setID_publication(rs.getInt("Id_Publication"));
                p.setListe_Signal(rs.getString("Liste_Signal"));
                p.setFichier((rs.getString("Fichier")));
                publications.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publications;
    }
            public String countpub(){
        String req="SELECT COUNT(Id_Publication) FROM publication";
        try {
            
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            //return "sqdqdsq";
        }     
        return "";
} 
 }
   
