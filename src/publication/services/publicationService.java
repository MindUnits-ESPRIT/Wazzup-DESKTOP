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
            if (P.getFichier().length()!=0)
            pste.setString(3,P.getFichier());
            else
               pste.setString(3,"NULL"); 
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
    public void Signaler_P(publication P,utilisateur U,int Type) {
        String Liste_Signal="";
        try {
        String req_fetch = "SELECT * FROM `publication_signaler` WHERE `Id_Publication`='"+P.getID_publication()+"' And `Id_Utilisateur`='"+U.getID_Utilisateur()+"'";
        ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(req_fetch);
              if(rs.next()==false)
              {
                  System.out.println("Saving Sign 1 time only");
         String req="INSERT INTO `publication_signaler`(`Type`, `Id_utilisateur`, `Id_publication`) VALUES (?,?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1,Type);
            pste.setInt(2,U.getID_Utilisateur());
            pste.setInt(3,P.getID_publication());
            pste.executeUpdate();
            System.out.println("Publication Signlé");
        } catch (SQLException ex1) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex1);
        }
              }
             else
             {
                 System.out.println("Publication déja signaler Par l'utilisateur "+U.getID_Utilisateur());
             }
        } catch (SQLException ex2) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex2);
        }
        //System.out.println(Liste_Signal);
        
        try {
            System.out.println(P.getID_publication());
             String req = "SELECT Count(Id_Publication) FROM `publication_signaler` WHERE `Id_Publication`='"+P.getID_publication()+"'";
            ste = conn.createStatement();
              ResultSet rs = ste.executeQuery(req);
              if(rs.next())
              if (rs.getInt(1)>=10)
              {
            String req2="UPDATE `publication` SET `Visibilite`=? WHERE `Id_Publication`='"+P.getID_publication()+"'";
        try {
            pste = conn.prepareStatement(req2);
            pste.setInt(1,2);
            pste.executeUpdate();
            System.out.println("Publication Banni !");
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }   
              }
              
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    

@Override
    public List Afficher_P() {
        List<publication> publications = new ArrayList<>();
        String req = "SELECT * FROM `publication`  WHERE `Visibilite`=1 ORDER BY Priority DESC,Date_Publication DESC";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                publication p = new publication();
                p.setID_publication(rs.getInt("Id_Publication"));
                p.setID_utilisateur(rs.getInt("Id_Utilisateur"));
                p.setDescription(rs.getString("Description"));
                p.setFichier(rs.getString("Fichier"));
                p.setDate(rs.getString("Date_Publication"));
                publications.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publications;
    }

    @Override
    public List<publication> Afficher_P(utilisateur U) {
           List<publication> publications = new ArrayList<>();
        String req = "SELECT P.* FROM `publication` P,`utilisateurs` U Where P.ID_Utilisateur='"+U.getID_Utilisateur()+"' and U.ID_Utilisateur=P.ID_Utilisateur and `Visibilite`=1 ORDER BY Priority DESC,Date_Publication DESC";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                publication p = new publication();
                utilisateur u = new utilisateur();
                p.setID_publication(rs.getInt("Id_Publication"));
                p.setID_utilisateur(rs.getInt("Id_Utilisateur"));
                p.setDescription(rs.getString("Description"));
                p.setFichier(rs.getString("Fichier"));
                p.setDate(rs.getString("Date_Publication"));
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
   
