package utils;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
/**
 * @author mouhib
 */
public class mail {
       String txt;
       String sub;
       String destinataire;
       String mail;
       String pwd;
       Session session;
    public String getTxt() {
        return txt;
    }
    public void setTxt(String txt) {
        this.txt = txt;
    }
    public String getSub() {
        return sub;
    }
    public void setSub(String sub) {
        this.sub = sub;
    }
    public String getDestinataire() {
        return destinataire;
    }
    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
  
    public mail(String txt, String sub, String destinataire) {
        this.txt = txt;
        this.sub = sub;
        this.destinataire = destinataire;
         Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
         this.mail ="wazzup.socialplatform@gmail.com";
         this.pwd="Stain4ever";
         this.session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, pwd);
            }
        });     
    }     
      public static Message prepareMessage(Session session, String mail, String destinataire,String txt,String sub) {
        Message msg = new MimeMessage(session);
        try {

            msg.setFrom(new InternetAddress(mail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));
            msg.setSubject(sub);
            msg.setText(txt);

        } catch (Exception ex) {
            //Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
} 
    
}
