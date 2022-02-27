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
        // Creation d'une instance de base de données 
        conn= db.getInstance().getCnx();
    }
    public int auth(utilisateur u){
        String authreq="SELECT * FROM `utilisateurs` WHERE `email`=? AND `mdp`=?";
 
        try {
            pste=conn.prepareStatement(authreq);
            pste.setString(1,u.getEmail());
            pste.setString(2,u.getMdp());
            ResultSet resauth= pste.executeQuery();
            if (resauth.next() == true) return(1);
        } catch (Exception e) {
            System.out.println(e+"ERREUR DE REQUETE");
        }
        return (0);
    }
    
         // Method CRUD#1 : Ajouter
    @Override
    public void ajouter(utilisateur u) {
       String reqverif="SELECT * FROM `utilisateurs` WHERE `nom`='"+u.getNom()+"' AND `prenom`='"+u.getPrenom()+"'"
               + "AND `email`='"+u.getEmail()+"'"; 
        try {
            pste= conn.prepareStatement(reqverif);
            ResultSet resFetch = pste.executeQuery();
            if (resFetch.next() == false)
          {
                    String req = "INSERT INTO `utilisateurs` (`nom`,`prenom`,`datenaissance`,`num_tel`,`genre`,`email`,`mdp`,`type_user`,`evaluation`) VALUES (?,?,?,?,?,?,?,?,?)";
      try {
          pste = conn.prepareStatement(req);
          pste.setString(1, u.getNom());
          pste.setString(2, u.getPrenom());
          pste.setString(3, u.getDatenaissance());
          pste.setInt(4, u.getNum_tel());
          pste.setString(5, u.getGenre());
          pste.setString(6, u.getEmail());
          pste.setString(7, u.getMdp());
          pste.setString(8, u.getType_user());
          pste.setInt(9, u.getEvaluation());
          pste.executeUpdate();
          System.out.println("Utilisateur creé");
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Utilisateur non creé "+ ex);
      }
          } else{
                System.out.println(" L'utilisateur existe déja ");
            }
          } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" L'utilisateur existe déja ");
        }
       

    }
    @Override
     public void ajouter_interet(int id,String interet) {
         String intreq="INSERT INTO `interets` (`nom_interet`,`ID_Utilisateur`) VALUES (?,?)";
         try {
          pste = conn.prepareStatement(intreq);
          pste.setString(1, interet);
          pste.setInt(2, id);
          pste.executeUpdate();
          System.out.println("L'interet a été bien accordé a l'utilisateur portant un ID = "+id);
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("L'interet ne peut pas etre accordé "+ ex);
      }
         
         
     }
          public void interet_utilisateur(int id) {
         String intusr="SELECT nom_interet,prenom,nom from interets inner join utilisateurs on interets.ID_Utilisateur=utilisateurs.ID_Utilisateur WHERE interets.ID_Utilisateur="+id;
         try {
          pste = conn.prepareStatement(intusr);
          ResultSet resFetch = pste.executeQuery();
            if (resFetch.next() == false)
          {
              System.out.println("L'utilisateur n'a aucun interet");
          } else {
                System.out.println("Les interets de L'utilisateur "+resFetch.getString(3)+" "+resFetch.getString(2)+" Sont : ");
                while(resFetch.next() == true)
                System.out.println(resFetch.getString(1));
            }
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("erreur de fetch"+ ex);
      }
         
     }
     
     
         // Method : Affichage
    @Override
    public List<utilisateur> afficher() {
        List<utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM `utilisateurs`";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            
            while(rs.next()){
                
                utilisateur u = new utilisateur();
                u.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setDatenaissance(rs.getString(4));
                u.setGenre(rs.getString(5));
                u.setNum_tel(rs.getInt(6));
                u.setEmail(rs.getString(7));
                u.setMdp(rs.getString(8));
                u.setType_user(rs.getString(9));
                u.setEvaluation(rs.getInt(10));
                utilisateurs.add(u);
            }
            } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilisateurs;
    }
         // Method CRUD#2: Modification
    @Override
    public void modifier(int i,utilisateur u) {
        System.out.println(u.getID_Utilisateur());
        String req="UPDATE `utilisateurs` SET `nom`=? , `prenom`=? ,`datenaissance`=? ,`num_tel`=? ,`genre`=? , `email`=? , `type_user`=? , `evaluation`=? WHERE `ID_Utilisateur`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,u.getNom());
            pste.setString(2,u.getPrenom());
            pste.setString(3,u.getDatenaissance());
            pste.setInt(4,u.getNum_tel());
            pste.setString(5,u.getGenre());
            pste.setString(6,u.getEmail());
            pste.setString(7,u.getType_user());
            pste.setInt(8,u.getEvaluation());
            pste.executeUpdate();
            System.out.println("Utilisateur bien modifié");
        } catch (SQLException ex) {
         System.out.println("Utilisateur n'a pas été modifié");
         Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         // Method CRUD#3: Suppression
    @Override

        public void supprimer(int i) {
               String req = "DELETE FROM `utilisateurs` WHERE `ID_Utilisateur` = '"+i+"' ";
        try {
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("Utilisateur supprimé avec success");

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Utilisateur non supprimé "+ ex);
        }
    }
        // Collaboration must be pushed from collaborationService to users table !!!!!!!!!
        @Override
        public void Get_Collaborations_list(int id){
            String req="SELECT ID_Collab,Nom_Collab FROM `salle_collaboration` as C, `utilisateurs` as U WHERE "
                    + "C.ID_Utilisateur='"+id+"'";
            try {
                pste= conn.prepareStatement(req);
                ResultSet rs= pste.executeQuery();
                while(rs.next()){
                System.out.println("Salles de collaborations de l'utilisateur : \n ID= "+rs.getString(1)+ "\n Nom Collab="+rs.getString(2));
                }
             } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Probleme d'importation des collaborations "+ ex);
      } 
            
        }
    
}
