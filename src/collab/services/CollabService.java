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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utilisateur.entities.utilisateur;

// @author mouhib
public class CollabService implements ICollab<Salle_Collaboration> {
    private Connection conn;
    // private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste1;
    private Statement ste;
    private Statement ste1;

    public CollabService() {
        conn = db.getInstance().getCnx();
    }

    @Override
    public int creer(Salle_Collaboration salle, int id_user) {
        // verfifier si Salle de collaboration existe deja
        String reqFetch = "SELECT * from `salle_collaboration` where  `Nom_Collab` = '" + salle.getNom_Collab()
                + "' AND `URL_Collab` = '" + salle.getURL_Collab() + "' AND `ID_Utilisateur` = '"
                + salle.getID_Utilisateur() + "' ";
        try {
            pste = conn.prepareStatement(reqFetch);
            ResultSet rs = pste.executeQuery();
            // si salle collaboration n'exist pas creer salle
            if (rs.next() == false) {
                String req = "INSERT INTO `salle_collaboration` (`Nom_Collab`, `URL_Collab`, `ID_Utilisateur`) VALUE ('"
                        + salle.getNom_Collab() + "','" + salle.getURL_Collab() + "','" + salle.getID_Utilisateur()
                        + "')";
                try {
                    ste = conn.createStatement();
                    ste.executeUpdate(req);
                    System.out.println("collab créée");
                    // fetch the auto generated collab id from databse and add it to the java object
                    // for later use
                    String req1 = "SELECT * FROM `salle_collaboration` WHERE `Nom_Collab` = ? AND `URL_Collab` = ? ";
                    try {
                        pste1 = conn.prepareStatement(req1);
                        pste1.setString(1, salle.getNom_Collab());
                        pste1.setString(2, salle.getURL_Collab());
                        ResultSet rs2 = pste1.executeQuery();
                        while (rs2.next()) {
                            System.out.println("ID du collab creer est : " + rs2.getInt("ID_Collab"));
                            salle.setID_Collab(rs2.getInt("ID_Collab"));
                        } 
                        String reqi = "INSERT INTO `collab_members` (`ID_Collab`, `ID_Utlisateur`) VALUE ( '"+salle.getID_Collab()+ "','" + id_user + "' )";
                        try {
                        pste1 = conn.prepareStatement(reqi);
                        pste1.executeUpdate(reqi);       
                    } catch (SQLException ex) {
                        Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("entry not added " + ex);
                    }  
                    } catch (SQLException ex) {
                        Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("ID non obtenu " + ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("collab non creer " + ex);
                }
                // ajout des user and collabs in collab members
                for (int i = 0; i < salle.getListe_Utilisateur().size(); i++) {
                    utilisateur e = (utilisateur) salle.getListe_Utilisateur().get(i);
                    String reqi = "INSERT INTO `collab_members` (`ID_Collab`, `ID_Utlisateur`) VALUE ('"
                            + salle.getID_Collab() + "','" + e.getID_Utilisateur() + "')";
                    try {
                        ste = conn.createStatement();
                        ste.executeUpdate(reqi);
                        System.out.println("entry added");
                    } catch (SQLException ex) {
                        Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("entry was not created" + ex);
                    }
                }
                // if salle de collaboration deja existe afficher message et arreter execution
            } else {
                System.out.println("Collab exists");
                return (0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Collab exists" + ex);
        }
        return (1);
    }

    // methode de modification du nom du salle collaboration appartir de l'id el
    // modification automatique du url
    @Override
    public void modifier(String nom, String nnom) {
        String URL = "http://example.com/" + nnom;
        String req = "UPDATE `salle_collaboration` SET `Nom_Collab`='" + nnom + "' , `URL_Collab`='" + URL
                + "'  WHERE `Nom_Collab`='" + nom + "'  ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("collab modifier avec success");
            System.out.println("Nouveau collab : " + nnom + " URL: " + URL);
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("collab non modifier " + ex);
        }
    }

    // methode de suppression du salle de collaboration appartir de l'id
    @Override
    public void supprimer(String nom) {
        String req = "DELETE FROM `salle_collaboration` WHERE `Nom_Collab` = '" + nom + "' ";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("collab supprimer avec success");
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("collab non supprimer " + ex);
        }
    }

    // methode d'affichage des liste de collaboration appartenant au utilisateur
    // specific
    @Override
    public ObservableList<Salle_Collaboration> afficher(int id) {
        ObservableList<Salle_Collaboration> salles = FXCollections.observableArrayList();
        String req = "SELECT * FROM salle_collaboration inner join collab_members ON collab_members.ID_Collab = salle_collaboration.ID_Collab where collab_members.ID_Utlisateur = '"+id+"' GROUP BY Nom_Collab";
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            System.out.println("salles de collaboration creer par l'utilisateur du id " + id + " sont:");
            while (rs.next()) {
                Salle_Collaboration s = new Salle_Collaboration();
                s.setID_Collab(rs.getInt("ID_Collab"));
                s.setNom_Collab(rs.getString("Nom_Collab"));
                s.setURL_Collab(rs.getString("URL_Collab"));
                salles.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Utilisateur non creé " + ex);
        }
        return salles;
    }
 
    @Override
    public ObservableList<utilisateur> afficherCollab_Membres(int id) {
        ObservableList<utilisateur> users = FXCollections.observableArrayList();
        String req = "SELECT ID_Utilisateur,CONCAT(prenom, ' ', nom) AS membre FROM `collab_members` inner join `utilisateurs` on utilisateurs.ID_Utilisateur = collab_members.ID_utlisateur where `ID_Collab` = '"+id+"'";
       
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
                System.out.println("les membre appartenant au collab du id " + id + " sont:");
                while (rs.next()) {
                    
                    utilisateur e = new utilisateur();
                    e.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                    e.setNom(rs.getString("membre"));
                    users.add(e);
                   
                }
              
           
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("membres not fetched " + ex);
        }
        return users;
    };

    @Override
    public int ajouter_membre(int idc, String id) {
        String req = "SELECT * FROM `utilisateurs` WHERE CONCAT(ID_Utilisateur,' ',prenom,' ',nom) = '"+id+"'";
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if (rs.next() == true) {
                String ids = rs.getString("ID_Utilisateur");
                String reqi = "INSERT INTO `collab_members` (`ID_Collab`, `ID_Utlisateur`) VALUE ('"
                        + idc + "','" + ids + "')";
                try {
                    ste = conn.createStatement();
                    ste.executeUpdate(reqi);
                    System.out.println("entry added");
                } catch (SQLException ex) {
                    Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("entry was not created" + ex);
                }

            } else {
                System.out.println("utilisateur n'existe pas");
                return (0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("user not fetched " + ex);
        }
        return (1);
    }

    @Override
    public Salle_Collaboration getSalleInfo(String nom) {
        String req = "SELECT * FROM `salle_collaboration` where `Nom_Collab` = '" + nom + "'";
        Salle_Collaboration s = new Salle_Collaboration();
        try {
            ste1 = conn.createStatement();
            ResultSet rs2 = ste1.executeQuery(req);

            while (rs2.next()) {
                s.setID_Collab(rs2.getInt("ID_Collab"));
                s.setNom_Collab(rs2.getString("Nom_Collab"));
                s.setURL_Collab(rs2.getString("URL_Collab"));
                s.setID_Utilisateur(rs2.getInt("ID_Utilisateur"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("id collab not fetched " + ex);
        }
        return s;
    }

    @Override
    public void supprimer_membre(int id, int idm) {

        String req4 = "DELETE from `collab_members` where ID_Collab = '" + id + "' and ID_Utlisateur = '" + idm + "'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req4);
            System.out.println("membre supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("membre non supprimer " + ex);
        }
    }

    @Override
    public utilisateur getinfo(String idu) {
        String req = "SELECT * FROM `utilisateurs` WHERE CONCAT(ID_Utilisateur,' ',prenom,' ',nom) = '"+idu+"'";
        utilisateur user = new utilisateur();
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while (rs.next()) {
                user.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setGenre(rs.getString(5));
                user.setNum_tel(rs.getInt(6));
                user.setEmail(rs.getString(7));
                user.setMdp(rs.getString(8));
                user.setListe_Collaborations(rs.getString(9));
                user.setType_user(rs.getString(10));
                user.setEvaluation(rs.getInt(11));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

     @Override
    public utilisateur getinfo2(int idu) {
        String req = "SELECT * FROM `utilisateurs` WHERE ID_Utilisateur = '"+idu+"'";
        utilisateur user = new utilisateur();
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            while (rs.next()) {
                user.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setGenre(rs.getString(5));
                user.setNum_tel(rs.getInt(6));
                user.setEmail(rs.getString(7));
                user.setMdp(rs.getString(8));
                user.setListe_Collaborations(rs.getString(9));
                user.setType_user(rs.getString(10));
                user.setEvaluation(rs.getInt(11));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    @Override
    public void Notificationmanager(int mode) {
           Notifications not = Notifications.create()      
                 .graphic(null)
                 .hideAfter(Duration.seconds(10))
                 .position(Pos.BOTTOM_RIGHT)
                 .onAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle (ActionEvent event) {
             System.out.println("clicked on notification");
         }
         }) ;
           not.darkStyle();
          switch(mode) {
  case 0:
   
   not.title("Success");
                 not.text("Membre ajouter et notifier par un mail" );
                 not.showInformation();
    break;
  case 1:
    
    not.title("Success ");
                 not.text("Suppression terminer");
                 not.showWarning();
    break;
    case 2:
     
                 not.text("Modification terminer");
                 not.title("Success");
                 not.showInformation();
    break;
    case 3:
     
                 not.text("Collaboration creer");
                 not.title("Success");
                 not.showConfirm();
    break;
    case 4:
     
                 not.text("Please create a project first");
                 not.title("No project found");
                 not.showWarning();
    break;
    case 8:
     
                 not.text("You are just a member in this collaboration");
                 not.title("Action interdit");
                 not.showWarning();
    break;
    case 9:
     
                 not.text("You can't remove yourself from \n your own collab");
                 not.title("Action interdit");
                 not.showWarning();
    break;
    case 10:
     
                 not.text("Félicitaion \n Projet trello créer");
                 not.title("Projet Créer");
                 not.showConfirm();
    break;
  default:
   
}  
           
    }

    @Override
    public ObservableList<utilisateur> afficherMembresNotInCollab(int idc) {
          ObservableList<utilisateur> users = FXCollections.observableArrayList();
        String req = "SELECT ID_Utilisateur,prenom,nom FROM `utilisateurs` WHERE (ID_Utilisateur,prenom, nom) NOT IN(SELECT ID_Utilisateur,prenom,nom FROM `Utilisateurs` inner join `collab_members` on utilisateurs.ID_Utilisateur = collab_members.ID_utlisateur where `ID_Collab` = '"+idc+"')GROUP BY nom,prenom ORDER BY ID_Utilisateur";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            
            while(rs.next()){
                
                utilisateur u = new utilisateur();
                u.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
             
                users.add(u);
            }
            } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }return users;
    }

}
