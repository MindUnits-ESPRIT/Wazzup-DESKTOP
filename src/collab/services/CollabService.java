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
import utilisateur.entities.utilisateur;
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
          String req = "INSERT INTO `salle_collaboration` (`Nom_Collab`, `URL_Collab`, `ID_Utilisateur`) VALUE ('" + salle.getNom_Collab() + "','" + salle.getURL_Collab() + "','" + salle.getID_Utilisateur() + "')";
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
               } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("collab non creer "+ ex);
        }                   
          // ajout des user and collabs in collab members
           for ( int i=0 ; i<salle.getListe_Utilisateur().size();i++){
                utilisateur e = (utilisateur) salle.getListe_Utilisateur().get(i);
               String reqi = "INSERT INTO `collab_members` (`ID_Collab`, `ID_Utlisateur`) VALUE ('" + salle.getID_Collab() + "','" + e.getID_Utilisateur() + "')";
                try {
            ste = conn.createStatement();
            ste.executeUpdate(reqi);
            System.out.println("entry added");              
                } catch(SQLException ex){
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("entry was not created"+ ex);
                 }                       
            }    
          // if salle de collaboration deja existe afficher message et arreter execution
          }
          else { 
              System.out.println("Collab exists");
              System.exit(0);              
          }                  
        } catch(SQLException ex){
           Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
           System.out.println("Collab exists"+ ex);
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
