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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                p.setFichier((rs.getString("Fichier")));
                publications.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(publicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publications;
    }
 }
   
