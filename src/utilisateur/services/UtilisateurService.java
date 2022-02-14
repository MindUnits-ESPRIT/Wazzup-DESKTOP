/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.services;
import org.json.simple.JSONArray;
import org.json.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilisateur.entities.utilisateur;
import database.db;


/**
 *
 * @author malek
 */
public class UtilisateurService implements Iutilisateur<utilisateur> {
    private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    
    public UtilisateurService() {
        conn= db.getInstance().getCnx();
    }

    @Override
    public void ajouter(utilisateur u) {
      String req = "INSERT INTO `utilisateurs` (`nom`,`prenom`,`age`,`num_tel`,`genre`,`email`,`mdp`,`type_user`,`evaluation`) VALUES (?,?,?,?,?,?,?,?,?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setString(1, u.getNom());
          pste.setString(2, u.getPrenom());
          pste.setInt(3, u.getAge());
          pste.setInt(4, u.getNum_tel());
          pste.setString(5, u.getGenre());
          pste.setString(6, u.getEmail());
          pste.setString(7, u.getMdp());
          pste.setString(8, u.getType_user());
          pste.setInt(9, u.getEvaluation());
          pste.executeUpdate();
          System.out.println("Utilisateur creé");
          String reqq="SELECT * FROM `utilisateurs` WHERE `nom` = ? AND `prenom` = ? AND `age`= ? AND `num_tel`= ? AND `genre`= ? AND `email`= ? AND `type_user`= ? AND `evaluation`= ?";
        try {
            pste = conn.prepareStatement(reqq);
            pste.setString(1,u.getNom() );
            pste.setString(2,u.getPrenom());
            pste.setInt(3,u.getAge());
            pste.setInt(4,u.getNum_tel());
            pste.setString(5,u.getGenre() );
            pste.setString(6,u.getEmail());
            pste.setString(7,u.getType_user() );
            pste.setInt(8,u.getEvaluation());
   
            ResultSet rs = pste.executeQuery();
             while(rs.next()){
//                System.out.println("ID du collab creer est : "+rs.getInt("ID_Collab"));
//                salle.setID_Collab(rs.getInt("ID_Collab"));
            }
                 
          
         
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("ID non obtenu "+ ex);
      } 
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Utilisateur non creé "+ ex);
      }
    }

    @Override
    public List<utilisateur> afficher() {
        List<utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM `utilisateurs`";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while(rs.next()){
                 JSONArray jsonArray2 = new JSONArray();
                utilisateur u = new utilisateur();
                u.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setAge(rs.getInt(4));
                u.setGenre(rs.getString(5));
                u.setNum_tel(rs.getInt(6));
                u.setEmail(rs.getString(7));
                u.setMdp(rs.getString(8));
                u.setType_user(rs.getString(10));
                u.setEvaluation(rs.getInt(11));
                u.setListe_Collaborations(rs.getString(9));
                System.out.println(rs.getString("Liste_Collaborations"));
                u.setType_user(rs.getString(10));
                utilisateurs.add(u);
            }
            } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilisateurs;
    }

    @Override
    public void modifier(utilisateur entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(utilisateur entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
