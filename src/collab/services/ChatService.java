package collab.services;
import collab.entities.chat;
import database.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilisateur.services.UtilisateurService;
// @author mouhi

public class ChatService implements IChat<chat>{
  private Connection conn;
    // private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste1;
    private Statement ste;
    private Statement ste1;
    public ChatService() {
        conn = db.getInstance().getCnx();
    }
    @Override
    public void send(chat chat) {  
                String req = "INSERT INTO message (ID_Uitlisateur, body ,ID_Collab ) VALUE ('"
                        + chat.getID_Uitlisateur() + "','" + chat.getBody() + "','" +chat.getID_Collab()
                        + "')";
                try {
                    ste = conn.createStatement();
                    ste.executeUpdate(req);                
                    // fetch the auto generated message id from databse and add it to the java object                  
                    String req1 = "SELECT * FROM `message` WHERE `ID_Collab` = '"+chat.getID_Collab()+"' AND `ID_Uitlisateur` = '"+chat.getID_Uitlisateur()+"' ";
                    try {
                        pste1 = conn.prepareStatement(req1);                        
                        ResultSet rs2 = pste1.executeQuery();
                        while (rs2.next()) {                          
                            chat.setId(rs2.getInt("id"));
                        }         
                    } catch (SQLException ex) {
                        Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("ID non obtenu " + ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CollabService.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("collab non creer " + ex);
                }       
    }

    @Override
    public void recive(int id_collab , int id_user) {
//           String req1 = "SELECT * FROM `message` WHERE `ID_Collab` = '"+id_collab+"' AND `ID_Utilisateur` != '"+id_user+"' ";
//                    try {
//                        pste1 = conn.prepareStatement(req1);                        
//                        ResultSet rs2 = pste1.executeQuery();
//                        while (rs2.next()) {                          
//                            chat.setId(rs2.getInt("id"));
//                        }         
//                    } catch (SQLException ex) {
//                        Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
//                        System.out.println("ID non obtenu " + ex);
//                    }
        
    }

    @Override
    public ObservableList<chat> afficher(int id_collab) {
        ObservableList<chat> ch = FXCollections.observableArrayList();
        String req = "SELECT * FROM message  inner join utilisateurs ON message.ID_Uitlisateur = utilisateurs.ID_Utilisateur where ID_Collab = '"+id_collab+"' order by id " ;
        try {
            pste = conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery();
            while (rs.next()) {
                chat s = new chat();
                s.setID_Collab(rs.getInt("ID_Collab"));
                s.setID_Uitlisateur(rs.getInt("ID_Uitlisateur"));
                s.setBody(rs.getString("body"));
                s.setNom(rs.getString("nom"));
                ch.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Utilisateur non creé " + ex);
        }
        return ch;
    }
    
    
}
