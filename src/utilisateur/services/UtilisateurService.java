/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
import utils.md5;

import utilisateur.entities.utilisateur;
import database.db;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import utilisateur.entities.interets;
import utils.SessionUser;
import utils.mailvalidation;


/**
 *
 * @author malek
 */
public class UtilisateurService implements Iutilisateur<utilisateur> {
    private Connection conn;
    public boolean added;
    public boolean modified;
    public boolean imageuploaded;
    boolean pwdmodified=false;
    
    //private Statement ste;
    private PreparedStatement pste;
    
    public UtilisateurService() {
        // Creation d'une instance de base de données 
        conn= db.getInstance().getCnx();
    }

    
    // Obtenir l'utilisateur par son  email pour l'utiliser en SESSION
        public utilisateur UserByEmail(String email) {
        utilisateur u = null;
        try {
            String findbyemail = "SELECT * FROM `utilisateurs` WHERE `email`=? ";
            pste = conn.prepareStatement(findbyemail);

            pste.setString(1, email);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                u = new utilisateur(
                        rs.getInt("ID_Utilisateur"),
                        rs.getString("nom"), 
                        rs.getString("prenom"), 
                        rs.getString("datenaissance"),
                        rs.getString("num_tel"), 
                        rs.getString("email"),
                        rs.getString("avatar"), 
                        rs.getString("mdp"), 
                        rs.getString("type_user"), 
                        rs.getInt("evaluation"),
                        rs.getString("genre"),
                        (Timestamp) rs.getObject("passwordRequestedAt"),
                        rs.getString("Token"), 
                        rs.getInt("nbsignal"), 
                        rs.getBoolean("activated"),
                        rs.getBoolean("sponsor"), 
                        rs.getBoolean("desactivated"), 
                        rs.getInt("creation_date")); 
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }    
    // Obtenir l'utilisateur par son ID
    public utilisateur UserById(Integer id) {
        utilisateur u = null;
        try {
            String findbyid = "SELECT * FROM `utilisateurs` WHERE `ID_Utilisateur`=? ";
            pste = conn.prepareStatement(findbyid);

            pste.setInt(1, id);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                u = new utilisateur(
                        rs.getInt("ID_Utilisateur"),
                        rs.getString("nom"), 
                        rs.getString("prenom"), 
                        rs.getString("datenaissance"),
                        rs.getString("num_tel"), 
                        rs.getString("email"),
                        rs.getString("avatar"), 
                        rs.getString("mdp"), 
                        rs.getString("type_user"), 
                        rs.getInt("evaluation"),
                        rs.getString("genre"),
                        (Timestamp) rs.getObject("passwordRequestedAt"),
                        rs.getString("Token"), 
                        rs.getInt("nbsignal"), 
                        rs.getBoolean("activated"),
                        rs.getBoolean("sponsor"), 
                        rs.getBoolean("desactivated"), 
                        rs.getInt("creation_date")); 
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }    
    
    /// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
     /// PHASE D'AUTEHNTIFICATION
    /// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    // Verification de l'activation d'un compte
        public boolean ActivatedCheck(String email) {
        boolean activated = false;
        try {
            String req = "SELECT `activated` FROM utilisateurs WHERE email=? ";
            PreparedStatement pste = conn.prepareStatement(req);
            pste.setString(1, email);
            ResultSet rs = pste.executeQuery();
            while (rs.next()) {
                if (rs.getInt("activated") == 1) {
                    activated = true;
                } else {
                    activated = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return activated;
    }
    // La fonction responsable a l'authentification
    public int auth(utilisateur u){
        String authreq="SELECT * FROM `utilisateurs` WHERE `email`=? AND `mdp`=?";
 
        try {
            pste=conn.prepareStatement(authreq);
            pste.setString(1,u.getEmail());
            pste.setString(2,u.getMdp());
            ResultSet resauth= pste.executeQuery();

            if (resauth.next() == true) {
                if (ActivatedCheck(u.getEmail())==true){
            u = this.UserById(resauth.getInt("ID_Utilisateur"));
            SessionUser.setUser(u);
            System.out.println("Vous etes connecté ! ");
            System.out.println("L'ID de l'utilisateur connecté est : "+u.getID_Utilisateur());
            return(1);
               }else {
                   System.out.println("Votre compte n'est pas encore activé ! ");
                   return (2);
                }
            }
        } catch (Exception e) {
            System.out.println(e+"ERREUR DE REQUETE");
        }
        return (0);
    }
     /// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Verification du mot de passe
        public boolean checkPassword(String pwd) {
    boolean Special = false;
    boolean Num = false;
    boolean Maj = false;
    boolean Min = false;
    String specialCharacters = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
   for (int i=0; i < pwd.length() ; i++)
        {
            char ch = pwd.charAt(i);
            if(specialCharacters.contains(Character.toString(ch))) {
             Special=true;
            }  
        }
        char c;
        for (int i = 0; i < pwd.length(); i++) {
            c = pwd.charAt(i);
            if (Character.isDigit(c)) {
                Num = true;
            } else if (Character.isUpperCase(c)) {
                Maj = true;
            } else if (Character.isLowerCase(c)) {
                Min = true;
            }
            if (Maj && Min && Num && Special && pwd.length()>5) {
                return true;
            }
        }
        return false;
    }
    /// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    // Verification du champ mail 
        public boolean isEmailExist(String email){
        String authreq="SELECT * FROM `utilisateurs` WHERE `email`=?";
 
        try {
            pste=conn.prepareStatement(authreq);
            pste.setString(1,email);
            ResultSet existRes= pste.executeQuery();
            if (existRes.next() == true) return(true);
        } catch (Exception e) {
            System.out.println(e+"ERREUR DE REQUETE");
        }
        return false;
    }
     // Verification du token   
        public Boolean CheckToken(String email, String token) {
        boolean checktoken = false;
        try {
            String req = "SELECT * FROM `utilisateurs` WHERE email=? AND Token=?";
            PreparedStatement pste = conn.prepareStatement(req);
            pste.setString(1, email);
            pste.setString(2, token);
            ResultSet rs = pste.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    checktoken = true;
                } else {
                    checktoken = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return checktoken;
    }    
    // Confirmation du compte
         
    public void ConfirmerCompte(String email) {
        String confirmaccount = "UPDATE utilisateurs SET activated=? , Token=? WHERE email=?  ";

        try {
            PreparedStatement pste = conn.prepareStatement(confirmaccount);
            pste.setInt(1, 1);
            pste.setString(2, null);
            pste.setString(3, email);
            mailvalidation.sendVerification(email,"Votre Compte a été activé","<h3> Bonjour </h3><br><p>Nous tenons à vous informer que votre compte a été activé avec succés . Nous vous souhaitons une bonne expérience sur notre plateforme.</p>");
            pste.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Verifier si l'utilisateur posséde une photo de profile         
    public boolean PictureCheck(int id) {
        boolean hasphoto=false;
        String check = "SELECT avatar FROM `utilisateurs` WHERE `ID_Utilisateur`="+id;
        
        try {
            PreparedStatement pste = conn.prepareStatement(check);
            ResultSet rs = pste.executeQuery(check);
            if (rs.next())   
          {
              if(rs.getString(1)==null){
               hasphoto=false;
              } else {
             hasphoto=true;
              }
          } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        
        }
        return hasphoto;
    }
    
    // Upload Profile picture
    
        public void PictureUpload(int id,String picture) {
        String upload = "UPDATE `utilisateurs` SET `avatar`=? WHERE `ID_Utilisateur`="+id;
         try {
            pste = conn.prepareStatement(upload);
            pste.setString(1,picture);
            pste.executeUpdate();
            System.out.println("Image mise a jour");
            imageuploaded=true;
        } catch (SQLException ex) {
         System.out.println("Erreur de modification d'image");
         imageuploaded=false;
         Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
      String req = "INSERT INTO `utilisateurs` (`nom`,`prenom`,`datenaissance`,`num_tel`,`genre`,`email`,`mdp`,`type_user`,`passwordRequestedAt`,`Token`,`activated`,`evaluation`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      try {
          String token = UUID.randomUUID().toString();
          pste = conn.prepareStatement(req);
          pste.setString(1, u.getNom());
          pste.setString(2, u.getPrenom());
          pste.setString(3, u.getDatenaissance());
          pste.setString(4, u.getNum_tel());
          pste.setString(5, u.getGenre());
          pste.setString(6, u.getEmail());
          pste.setString(7, u.getMdp());
          pste.setString(8, u.getType_user());
          pste.setObject(9, u.getPasswordRequestedAt());
          pste.setString(10,token);
          pste.setInt(11,0);
          pste.setInt(12, u.getEvaluation());
          pste.executeUpdate();
          System.out.println("Utilisateur creé");
          mailvalidation.sendVerification(u.getEmail(), "Activation du compte Wazzup",
                "<!DOCTYPE html><html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\"><head><title></title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Bitter\" \n" +
"rel=\"stylesheet\" type=\"text/css\"><!--<![endif]--><style>*{box-sizing:border-box}body{margin:0;padding:0}a[x-apple-data-detectors]{color:inherit!important;text-decoration:inherit!important}#MessageViewBody a{color:inherit;text-decoration:none}p{line-height:inherit}@media (max-width:660px){.row-content{width:100%!important}.column .border{display:none}.stack .column{width:100%;display:block}.row-3 .column-1{border-right:30px solid #fff;border-left:30px solid #fff}}</style></head><body \n" +
"style=\"background-color:#f8f8f9;margin:0;padding:0;-webkit-text-size-adjust:none;text-size-adjust:none\"><table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#f8f8f9\"><tbody><tr><td><table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#1aa19c\"><tbody><tr><td>\n" +
"<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#1aa19c;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0;font-weight:400;text-align:left;vertical-align:top;padding-top:0;padding-bottom:0;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"divider_block\" width=\"100%\" \n" +
"border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td><div align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"divider_inner\" style=\"font-size:1px;line-height:1px;border-top:4px solid #1aa19c\"><span>&#8202;</span></td></tr></table></div></td></tr></table></td></tr></tbody></table></td></tr></tbody></table><table \n" +
"class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td><table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column column-1\" width=\"100%\" \n" +
"style=\"mso-table-lspace:0;mso-table-rspace:0;font-weight:400;text-align:left;vertical-align:top;padding-top:0;padding-bottom:0;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"width:100%;padding-right:0;padding-left:0\"><div align=\"center\" style=\"line-height:10px\"><img \n" +
"src=\"https://d15k2d11r6t6rl.cloudfront.net/public/users/BeeFree/beefree-euv19stvplc/editor_images/wazzup.png\" style=\"display:block;height:auto;border:0;width:256px;max-width:100%\" width=\"256\"></div></td></tr></table><table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;word-break:break-word\"><tr><td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px\"><div \n" +
"style=\"font-family:Georgia,'Times New Roman',serif\"><div style=\"font-size:12px;font-family:Bitter,Georgia,Times,'Times New Roman',serif;mso-line-height-alt:14.399999999999999px;color:#555;line-height:1.2\"><p style=\"margin:0;font-size:16px;text-align:center\"><span style=\"font-size:26px;\"><strong>Activez votre compte&nbsp;</strong></span></p></div></div></td></tr></table><table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" \n" +
"style=\"mso-table-lspace:0;mso-table-rspace:0;word-break:break-word\"><tr><td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px\"><div style=\"font-family:sans-serif\"><div style=\"font-size:12px;mso-line-height-alt:18px;color:#555;line-height:1.5;font-family:Montserrat,Trebuchet MS,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Tahoma,sans-serif\"><p style=\"margin:0;font-size:12px\"><strong>Bienvenue "+u.getPrenom()+" "+u.getNom()+"!</strong><br>\n" +
"Nous sommes heureux que vous ayez rejoint notre communauté . Il vous suffit de confirmer votre inscription à l'aide du code ci-dessous.</p></div></div></td></tr></table><table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"padding-top:50px\"><div align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" \n" +
"style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"divider_inner\" style=\"font-size:1px;line-height:1px;border-top:0 solid #bbb\"><span>&#8202;</span></td></tr></table></div></td></tr></table></td></tr></tbody></table></td></tr></tbody></table><table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td><table class=\"row-content stack\" align=\"center\" border=\"0\" \n" +
"cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#f3fafa;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0;font-weight:400;text-align:left;vertical-align:top\"><table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"border\" style=\"width:30px;background-color:#fff\">\n" +
"&nbsp;</td><td class=\"content_blocks\" style=\"padding-top:0;padding-bottom:0;border-top:0;border-bottom:0;width:580px\"><table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td><div align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"divider_inner\" \n" +
"style=\"font-size:1px;line-height:1px;border-top:4px solid #1aa19c\"><span>&#8202;</span></td></tr></table></div></td></tr></table><table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"padding-top:25px\"><div align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"divider_inner\" \n" +
"style=\"font-size:1px;line-height:1px;border-top:0 solid #bbb\"><span>&#8202;</span></td></tr></table></div></td></tr></table><table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;word-break:break-word\"><tr><td style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;padding-top:10px\"><div style=\"font-family:sans-serif\"><div \n" +
"style=\"font-size:12px;mso-line-height-alt:14.399999999999999px;color:#555;line-height:1.2;font-family:Montserrat,Trebuchet MS,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Tahoma,sans-serif\"><p style=\"margin:0;font-size:16px;text-align:center\"><span style=\"color:#2b303a;font-size:18px;\"><strong>Votre Token d'activation</strong></span></p></div></div></td></tr></table><table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" \n" +
"style=\"mso-table-lspace:0;mso-table-rspace:0;word-break:break-word\"><tr><td style=\"padding-bottom:32px\"><div style=\"font-family:sans-serif\"><div style=\"font-size:12px;mso-line-height-alt:14.399999999999999px;color:#555;line-height:1.2;font-family:Montserrat,Trebuchet MS,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Tahoma,sans-serif\"><p style=\"margin:0;font-size:16px;text-align:center\"><span style=\"color:#1aa19c;font-size:38px;\"><span style><strong>"+token+"</strong></span></span></p></div></div>\n" +
"</td></tr></table></td><td class=\"border\" style=\"width:30px;background-color:#fff\">&nbsp;</td></tr></table></td></tr></tbody></table></td></tr></tbody></table><table class=\"row row-4\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td><table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" \n" +
"style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0;font-weight:400;text-align:left;vertical-align:top;padding-top:0;padding-bottom:0;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td \n" +
"style=\"padding-bottom:12px;padding-top:10px\"><div align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"divider_inner\" style=\"font-size:1px;line-height:1px;border-top:0 solid #bbb\"><span>&#8202;</span></td></tr></table></div></td></tr></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><!-- End --></body></html>"
          );
                  added=true;
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("Utilisateur non creé "+ ex);
          added=false;
      }         catch (MessagingException ex) {
                    Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
                }
          } else{
                System.out.println(" L'utilisateur existe déja ");
                added=false;
                
            }
          } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" L'utilisateur existe déja ");
        }
       

    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++// 
/////////////////////////////////////////////////////// INTERETS UTILISATEUR ////////////////////////////////////////////////////////
    
   
            public List<String> getAllInterets_Combobox(){
         String req="SELECT COLUMN_TYPE FROM information_schema.`COLUMNS` WHERE TABLE_NAME = 'interets' AND COLUMN_NAME = 'nom_interet'";
         List<String> interets = new ArrayList<>();     
         try {
          pste = conn.prepareStatement(req);
         ResultSet resFetch = pste.executeQuery();
                   if (resFetch.next())
          {
        String interet = resFetch.getString(1);
        interet=interet.substring(5,interet.length()-1);
        String [] List = interet.split(",");

        for(String s:List){
        interets.add(s.substring(1, s.length()-1));
        
        }
          } else {
              System.out.println("Aucun interets");
            }
                } catch (Exception e) {
                }
             return interets;
    }
        public List<interets> getAllInterets(int id){
         String req="SELECT nom_interet from interets inner join utilisateurs on interets.ID_Utilisateur=utilisateurs.ID_Utilisateur WHERE interets.ID_Utilisateur='"+id+"'";
           List<interets> all_interets = new ArrayList<>();  

         try {
          pste = conn.prepareStatement(req);
         ResultSet resFetch = pste.executeQuery();
                   while (resFetch.next())
          {
          interets listinteret=new interets();
          listinteret.setNom_interet(resFetch.getString(1));
          all_interets.add(listinteret);
        
          } 
                } catch (Exception e) {
                }
//              System.out.println(all_interets);
             return all_interets;
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
          public List<String> interet_utilisateur(int id) {
         String intusr="SELECT nom_interet,prenom,nom from interets inner join utilisateurs on interets.ID_Utilisateur=utilisateurs.ID_Utilisateur WHERE interets.ID_Utilisateur="+id;
         List<String> interet_user = new ArrayList<>(); 
         try {
          pste = conn.prepareStatement(intusr);
          ResultSet resFetch = pste.executeQuery();
            if (resFetch.next() == false)
          {   
              System.out.println("L'utilisateur n'a aucun interet");
          } else {
                interet_user.add(resFetch.getString(1));
                while(resFetch.next() == true)
                System.out.println(resFetch.getString(1));
            }
      } catch(SQLException ex){
          Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE,null,ex);
          System.out.println("erreur de fetch"+ ex);
      }
        return interet_user; 
     }
  //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++// 
     ////////// GENERATION D'UN MOT DE PASSE POUR MOT DE PASSE OUBLIE
          public static String generatePassword(int len)
         {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    } 
          
   //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//       
         // Method : Affichage
    @Override
    public List<utilisateur> afficher() {
        List<utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM `utilisateurs` WHERE type_user='User'";
        
        try{
            pste= conn.prepareStatement(req);
            ResultSet rs = pste.executeQuery(req);
            
            while(rs.next()){
                
                utilisateur u = new utilisateur();
                u.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                u.setEmail(rs.getString("email"));
                u.setEvaluation(rs.getInt("evaluation"));
                u.setSponsor(rs.getBoolean("sponsor"));
                u.setActivated(rs.getBoolean("activated"));
                utilisateurs.add(u);
            }
            } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilisateurs;
    }
         // Method CRUD#2: Modification
    @Override
    public void modifier(int i,utilisateur u, int modif) {
        if (modif == 1) {
        String req="UPDATE `utilisateurs` SET `nom`=? , `prenom`=? ,`datenaissance`=? ,`num_tel`=? ,`genre`=? , `email`=? , `type_user`=? , `evaluation`=? WHERE `ID_Utilisateur`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,u.getNom());
            pste.setString(2,u.getPrenom());
            pste.setString(3,u.getDatenaissance());
            pste.setString(4,u.getNum_tel());
            pste.setString(5,u.getGenre());
            pste.setString(6,u.getEmail());
            pste.setString(7,u.getType_user());
            pste.setInt(8,u.getEvaluation());
            pste.executeUpdate();
            System.out.println("Utilisateur bien modifié");
            modified=true;
        } catch (SQLException ex) {
         System.out.println("Utilisateur n'a pas été modifié");
         modified=false;
         Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else if (modif == 2){
        String req="UPDATE `utilisateurs` SET `datenaissance`=? ,`num_tel`=? ,`genre`=? , `email`=? ,`mdp`=? WHERE `ID_Utilisateur`='"+i+"'";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1,u.getDatenaissance());
            pste.setString(2,u.getNum_tel());
            pste.setString(3,u.getGenre());
            pste.setString(4,u.getEmail());
            pste.setString(5,md5.getMd5(u.getMdp()));
            pste.executeUpdate();
            System.out.println("Utilisateur bien modifié");
            modified=true;
        } catch (SQLException ex) {
         System.out.println("Utilisateur n'a pas été modifié");
         modified=false;
         Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    //// UPDATE PASSWORD ///
    public void modifierPassword(int id,String email) throws MessagingException{
        String generatedpassword=generatePassword(8);
        String req="UPDATE `utilisateurs` SET `mdp`=? WHERE `ID_Utilisateur`="+id;
        try{
            pste= conn.prepareStatement(req);
            pste.setString(1,md5.getMd5(generatedpassword));
            pste.executeUpdate();
             mailvalidation.sendVerification(email,"Votre nouveau mot de passe","Votre nouveau mot de passe = "+generatedpassword);
           } catch (SQLException ex) {
         System.out.println("Mot de passe modifié");
         Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void ModificationPasswordProfile(int id,String pwd){
   
        String req="UPDATE `utilisateurs` SET `mdp`=? WHERE `ID_Utilisateur`="+id;
        try{
            pste= conn.prepareStatement(req);
            pste.setString(1,md5.getMd5(pwd));
            pste.executeUpdate();
           } catch (SQLException ex) {
         System.out.println("Mot de passe modifié");
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
