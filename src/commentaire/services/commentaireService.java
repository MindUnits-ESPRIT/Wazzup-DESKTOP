/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentaire.services;

import commentaire.entities.commentaire;
import java.util.List;
import utilisateur.entities.utilisateur;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import database.db;
import java.util.ArrayList;
import publication.entities.publication;
/**
 *
 * @author Misow3002
 */
public class commentaireService implements Icommentaire<commentaire> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    
    public commentaireService() {
    conn = db.getInstance().getCnx();
}
    @Override
    public void Creer_C(publication P,commentaire C,utilisateur U) {
        String req="INSERT INTO `commentaire`(`Id_Publication`,`Id_Utilisateur`,`Message`) VALUES(?,?,?)";
        try {
            System.out.println("Cc : "+U.getID_Utilisateur());
            pste = conn.prepareStatement(req);
            pste.setInt(1,P.getID_publication());
            pste.setInt(2,U.getID_Utilisateur());
            //Modifier ICI !
            pste.setString(3,"MESSAGE TEXTO");
            pste.executeUpdate();
            System.out.println("Commentaire Publié");
        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void Modifier_C(publication P,commentaire C) {
        String req="UPDATE `commentaire` SET `Message`=?,`Date`=CURRENT_TIMESTAMP WHERE `Id_Commentaire`='"+C.getID_Commentaire()+"' And `Id_Publication`='"+C.getID_publication()+"' And `Id_Utilisateur`='"+C.getID_utilisateur()+"'";
                try {
            pste = conn.prepareStatement(req);
            pste.setString(1,"FIXED Message");
            pste.executeUpdate();
            System.out.println("Publication Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Supprimer_C(publication P,commentaire C) {
        String req="DELETE FROM `commentaire` WHERE `Id_Publication`='"+P.getID_publication()+"' And `Id_Utilisateur`='"+C.getID_utilisateur()+"' And `Id_Commentaire`='"+C.getID_Commentaire()+"'";
        System.out.println(req);
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Publication Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    @Override
    public List Afficher_C() {
              List<commentaire> commentaires = new ArrayList<>();
        String req = "SELECT * FROM `commentaire`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                commentaire c = new commentaire();
                c.setID_publication(rs.getInt("Id_Publication"));
                c.setID_utilisateur(rs.getInt("Id_Utilisateur"));
                c.setMessage((rs.getString("Message")));
                c.setDate((rs.getString("Date")));
                commentaires.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commentaires;
    } 
}
