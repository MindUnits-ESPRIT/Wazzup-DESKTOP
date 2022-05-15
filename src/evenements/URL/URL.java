/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements.URL;

import Rencontre.services.RencontreService;
import database.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SRN
 */
public class URL {
    private Connection conn;  
    private Statement ste;
    private PreparedStatement pste;
    private String url;
    public String GetUrl_SalleCinema(int ID_U) throws SQLException{
        
        
        conn= db.getInstance().getCnx();
        String req = "SELECT `ID_Event`,`Nom_Salle` FROM `evenement` NATURAL join `salle_cinema` where `ID_Utilisateur`='"+ID_U+"'";
             
         try{
             ste=conn.createStatement();
             ResultSet rs = ste.executeQuery(req);
           // pste= conn.prepareStatement(req);
           while(rs.next()){
               int ID_E=rs.getInt("ID_Event");
               String Nom_S = rs.getString("Nom_Salle");
                      url= "http://Wazzup.com/SalleCinema/"+ID_U+"/"+ID_E+"/"+Nom_S;
              System.out.println("Url_S cree");
               System.out.println(ID_U);
               System.out.println(ID_E);
               System.out.println(Nom_S);
               
           }
            } catch (SQLException ex) {
            Logger.getLogger(RencontreService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Url_S non cree");
        }

   /** int ID_U=u.getID_Utilisateur();
    int ID_S= sc.getID_Salle();
    String nom_salle= sc.getNomSalle();
    String url= "http://Wazzup.com/SalleCinema/"+ID_U+"/"+ID_S+"/"+nom_salle;**/
    return url;
    }
     public String GetUrl_Rencontre(int ID_U){
          conn= db.getInstance().getCnx();
          String req = "SELECT `ID_Event`,`Type_Rencontre` FROM `evenement` NATURAL join `rencontre` where `ID_Utilisateur`='"+ID_U+"'";
           try{
             ste=conn.createStatement();
             ResultSet rs = ste.executeQuery(req);
             while(rs.next()){
    int ID_R= rs.getInt("ID_Event");
    String Type_Rencontre=rs.getString("Type_Rencontre");
         url= "http://Wazzup.com/Rencontre/"+ID_U+"/"+ID_R+"/"+Type_Rencontre;      
             }
             System.out.println("Url_R cree");
     } catch (SQLException ex) {
            Logger.getLogger(RencontreService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("Url_R non cree");
        }
    return url;
    }

    public String GetUrl_Rencontre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     /**public URL TakeURL(){
         SalleCinema sc = new SalleCinema(); // TODO Auto-generated catch block
         String url = sc.getURL_Film();
try {
    URLConnection con = url.openConnection();
    in.close();
} catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
}
        return null;
     }**/
}
