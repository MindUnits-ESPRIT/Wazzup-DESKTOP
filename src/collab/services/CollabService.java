package collab.services;
import collab.entities.Salle_Collaboration;
import database.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilisateur.services.UtilisateurService;

/**
 *
 * @author mouhi
 */
public class CollabService implements ICollab<Salle_Collaboration> {
private Connection conn;
    
    //private Statement ste;
    private PreparedStatement pste;
    private Statement ste;
    public CollabService() {
        conn= db.getInstance().getCnx();
    }
    @Override
    public void creer(Salle_Collaboration salle) {
        String req = "INSERT INTO `salle_collaboration` (`Liste_Utilisateur`, `Nom_Collab`, `URL_Collab`) VALUE ('" + salle.getListe_Utilisateur() + "','" + salle.getNom_Collab() + "','" + salle.getURL_Collab() + "')";
     try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("collab créée");
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE,null,ex);
             System.out.println("collab non creer "+ ex);
        }
    }

    @Override
    public void modifier(Salle_Collaboration entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Salle_Collaboration entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Salle_Collaboration> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
