package collab.services;
import collab.entities.Projet;
import collab.entities.Salle_Collaboration;
import database.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilisateur.services.UtilisateurService;

/**
 *
 * @author mouhi
 */
public class ProjetService implements IProjet<Projet> {
private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    private Statement ste;
    public ProjetService() {
        conn= db.getInstance().getCnx();
        
        
        
    }
    @Override
    public void creer(Projet projet ) {
        String req = "INSERT INTO `projet` ( `ID_Collab`,`Nom_Projet`,`Description_Projet`, `URL_trello`) VALUE ('" + projet.getID_Collab() + "','" + projet.getNom_Projet()
                + "','" + projet.getDescription() + "','" + projet.getURL_Trello() + "')";
     try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Projet créée");
            String req1 = "SELECT * FROM `projet` WHERE `ID_Collab` = ? AND `Nom_Projet` = ? ";
             try {
            pste = conn.prepareStatement(req1);
            pste.setInt(1, projet.getID_Collab());
            pste.setString(2, projet.getNom_Projet());
            ResultSet rs = pste.executeQuery();
             while(rs.next()){
                System.out.println("ID du projet creer est : "+rs.getInt("ID_Projet"));
                projet.setID(rs.getInt("ID_Projet"));
            }
                 
          
         
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("ID non obtenu "+ ex);
      } 
                  
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Projet non creer "+ ex);
        } 
    }

    @Override
    public void modifier(int id, String nom, String des) {
       String URL = "http://trello.com/"+nom;
        String req = "UPDATE `projet` SET `Nom_Projet`='"+nom+"' , `Description_Projet`='"+des+"' , `URL_trello` = '"+URL+"'  WHERE `ID_Projet`='"+id+"'  ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Projet modifier avec success");
            System.out.println("Nouveau Projet : "+nom+ " :"+des + " URL: "+URL);
                  
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Projet non modifier "+ ex);
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `projet` WHERE `ID_Projet` = '"+id+"' ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Projet supprimer avec success");
           
                  
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("Projet non supprimer "+ ex);
        }
    }

    @Override
    public List<Projet> afficher(int id) {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT * FROM `projet` where `ID_Collab` = ? ";
        try {
          pste = conn.prepareStatement(req);
          pste.setInt(1, id);
          ResultSet rs = pste.executeQuery();
          System.out.println("projet associes a cette salle de collaboration sont:");
          while(rs.next()){
                Projet p = new Projet();
                p.setID(rs.getInt("ID_Projet"));
                p.setID_Collab(rs.getInt("ID_Collab"));
                p.setNom_Projet(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setURL_Trello(rs.getString(5));
                projets.add(p);
            }
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Utilisateur non creé "+ ex);
      } 
        return projets ;
    }
    }

   

