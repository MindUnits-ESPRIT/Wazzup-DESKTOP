package collab.services;
import collab.entities.Salle_Collaboration;
import database.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utilisateur.services.UtilisateurService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
// @author mouhib
public class CollabService implements ICollab<Salle_Collaboration> {
private Connection conn; 
    //private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste1;
    private PreparedStatement pste2;
    private PreparedStatement pste3;
    private Statement ste;
    private String Liste_Collabs ;
    public CollabService() {
        conn= db.getInstance().getCnx();
    }
    @Override
    public void creer(Salle_Collaboration salle , int id_user ) {
        // verfifier si Salle de collaboration existe deja 
         String reqFetch = "SELECT * from `salle_collaboration` where  `Nom_Collab` = '" + salle.getNom_Collab() + "' AND `URL_Collab` = '" + salle.getURL_Collab() + "' AND `ID_Utilisateur` = '" + salle.getID_Utilisateur() + "' ";
         try {
          pste = conn.prepareStatement(reqFetch);
          ResultSet rs = pste.executeQuery();
       // si salle collaboration n'exist pas creer salle   
          if (rs.next() == false)
          {
          String req = "INSERT INTO `salle_collaboration` (`Liste_Utilisateur`, `Nom_Collab`, `URL_Collab`, `ID_Utilisateur`) VALUE ('" + salle.getListe_Utilisateur() + "','" + salle.getNom_Collab() + "','" + salle.getURL_Collab() + "','" + salle.getID_Utilisateur() + "')";
          try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("collab créée");  
       // fetch the auto generated collab id from databse and add it to the java object for later use    
            String req1 = "SELECT * FROM `salle_collaboration` WHERE `Nom_Collab` = ? AND `URL_Collab` = ? ";     
             try {               
            pste1 = conn.prepareStatement(req1);  
            pste1.setString(1, salle.getNom_Collab());
            pste1.setString(2, salle.getURL_Collab());
            ResultSet rs2 = pste1.executeQuery();          
             while(rs2.next()){      
                System.out.println("ID du collab creer est : "+rs2.getInt("ID_Collab"));
                salle.setID_Collab(rs2.getInt("ID_Collab"));
             }  
               } catch(SQLException ex){
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("ID non obtenu "+ ex);
                 } 
         // fetch the collaborations liste of the active user to update it with the new collab we just created  
              String req2 = "SELECT * FROM `utilisateurs` WHERE `ID_Utilisateur` = '"+id_user+"'  ";     
             try {               
            pste2 = conn.prepareStatement(req2);
            ResultSet rs3 = pste2.executeQuery();           
             while(rs3.next()){      
                Liste_Collabs = rs3.getString("Liste_Collaborations");           
                 JSONArray array = new JSONArray(Liste_Collabs);
                 JSONObject user = new JSONObject();
                 user.put("ID",salle.getID_Collab());
                 array.put(user) ;
         // update the user table with the new collabs liste we updated earlier       
                  String req3 = "UPDATE `utilisateurs` SET `Liste_Collaborations`= '"+array+"' WHERE ID_Utilisateur = '"+id_user+"' ";     
             try {               
            pste3 = conn.prepareStatement(req3);
            pste3.executeUpdate();
                 System.out.println("User collabs updated");   
         // catch clauses         
               } catch(SQLException ex){
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Users table not updated "+ ex);
                 }    
            }  
               } catch(SQLException ex){
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("liste non obtenu "+ ex);
                 }         
               } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("collab non creer "+ ex);
        }          
          }
          // if salle de collaboration deja existe afficher message et arreter execution
          else { 
              System.out.println("Collaboration exists");
              System.exit(0);              
          }                  
        } catch(SQLException ex){
           Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
           System.out.println("User exists"+ ex);
        }            
    }
    // methode de modification du nom du salle collaboration appartir de l'id el modification automatique du url 
    @Override
    public void modifier(int id , String nom) {
        String URL = "http://example.com/"+nom;
        String req = "UPDATE `salle_collaboration` SET `Nom_Collab`='"+nom+"' , `URL_Collab`='"+URL+"'  WHERE `ID_Collab`='"+id+"'  ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("collab modifier avec success");
            System.out.println("Nouveau collab : "+nom+ " URL: "+URL);
                  
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("collab non modifier "+ ex);
        }
    }
    // methode de suppression du salle de collaboration appartir de l'id
    @Override
    public void supprimer(int id) {
       String req = "DELETE FROM `salle_collaboration` WHERE `ID_Collab` = '"+id+"' ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("collab supprimer avec success");
           
                  
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("collab non supprimer "+ ex);
        }
    }
    // methode d'affichage des liste de collaboration appartenant au utilisateur specific
    @Override
    public List<Salle_Collaboration> afficher(int id) {     
    List<Salle_Collaboration> salles = new ArrayList<>();
        String req = "SELECT * FROM `salle_collaboration` where `ID_Utilisateur` = ? ";
        try {
          pste = conn.prepareStatement(req);
          pste.setInt(1, id);
          ResultSet rs = pste.executeQuery();
          System.out.println("salles de collaboration creer par l'utilisateur du id "+id+" sont:");
          while(rs.next()){
                Salle_Collaboration s = new Salle_Collaboration();
                s.setID_Collab(rs.getInt("ID_Collab"));
                s.setNom_Collab(rs.getString(3));
                s.setURL_Collab(rs.getString(4));               
                salles.add(s);               
            }
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Utilisateur non creé "+ ex);
      } 
        return salles ;
    }
}
