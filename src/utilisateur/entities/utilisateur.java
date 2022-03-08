/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.entities;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import org.json.simple.JSONArray;
import static utils.md5.getMd5;

/**
 *
 * @author malek
 */
public class utilisateur {
    private int ID_Utilisateur;
    private String nom;
    private String prenom;
    private String datenaissance;
    private String num_tel;
    private String email;
    private String avatar;
    private String mdp;
    private String type_user;
    private int evaluation;
    private String genre;
    private Timestamp passwordRequestedAt;
    private String Token;
    private int nbsignal;
    private boolean activated;
    private boolean sponsor;
    private boolean desactivated;
    private String Liste_Collaborations ;
    
    private int date_creation;

//    public utilisateur(int ID_Utilisateur, String nom, String prenom, String datenaissance, String genre, String num_tel, String email, String mdp,String type_user, Timestamp passwordRequestedAt, String Token, boolean activated,int nbsignal,int evaluation,int date_creation) {
//        this.ID_Utilisateur = ID_Utilisateur;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.datenaissance = datenaissance;
//        this.num_tel = num_tel;
//        this.email = email;
//        this.mdp = getMd5(mdp);
//        this.type_user = type_user;
//        this.evaluation = evaluation;
//        this.genre = genre;
//        this.passwordRequestedAt = passwordRequestedAt;
//        this.Token = Token;
//        this.nbsignal = nbsignal;
//        this.activated = activated;
//        this.date_creation = date_creation;
//    }

    public utilisateur(int ID_Utilisateur, String nom, String prenom, String datenaissance, String num_tel, String email, String avatar, String mdp, String type_user, int evaluation, String genre, Timestamp passwordRequestedAt, String Token, int nbsignal, boolean activated, boolean sponsor, boolean desactivated, int date_creation) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.num_tel = num_tel;
        this.email = email;
        this.avatar = avatar;
        this.mdp = mdp;
        this.type_user = type_user;
        this.evaluation = evaluation;
        this.genre = genre;
        this.passwordRequestedAt = passwordRequestedAt;
        this.Token = Token;
        this.nbsignal = nbsignal;
        this.activated = activated;
        this.sponsor = sponsor;
        this.desactivated = desactivated;
        this.date_creation = date_creation;
    }



    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public utilisateur(String nom, String prenom, String datenaissance, String num_tel, String email, String mdp, String type_user, int evaluation, String genre, String interet, Timestamp passwordRequestedAt, String Token, int nbsignal, boolean activated, int date_creation) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.num_tel = num_tel;
        this.email = email;
        this.mdp = getMd5(mdp);
        this.type_user = type_user;
        this.evaluation = evaluation;
        this.genre = genre;
        this.passwordRequestedAt = passwordRequestedAt;
        this.Token = Token;
        this.nbsignal = nbsignal;
        this.activated = activated;
        this.date_creation = date_creation;
    }
    
   
   

    public Timestamp getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Timestamp passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(int nbsignal) {
        this.nbsignal = nbsignal;
    }

    public utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = getMd5(mdp); 
    }
    
    

    public utilisateur() {
    }

    public utilisateur(String nom, String prenom, String datenaissance, String num_tel, String genre,String email, String mdp,String type_user) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.num_tel = num_tel;
        this.email = email;
        this.mdp = getMd5(mdp);
        this.genre = genre;
        this.type_user = type_user;
    }
    

    public utilisateur(int ID_Utilisateur, String nom, String prenom) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }
    
    public utilisateur(String nom, String prenom, String datenaissance, String num_tel, String email, String mdp, String type_user, int evaluation , String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.num_tel = num_tel;
        this.email = email;
        this.mdp = getMd5(mdp);
        this.type_user = type_user;
        this.evaluation = evaluation;
        this.genre=genre;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }


    public String getNum_tel() {
        return num_tel;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getType_user() {
        return type_user;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }
    

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    
    public int getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(int date_creation) {
        this.date_creation = date_creation;
    }

    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getListe_Collaborations() {
        return Liste_Collaborations;
    }

    public void setListe_Collaborations(String Liste_Collaborations) {
        this.Liste_Collaborations = Liste_Collaborations;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isSponsor() {
        return sponsor;
    }

    public void setSponsor(boolean sponsor) {
        this.sponsor = sponsor;
    }

    public boolean isDesactivated() {
        return desactivated;
    }

    public void setDesactivated(boolean desactivated) {
        this.desactivated = desactivated;
    }


    @Override
    public String toString() {
        return "utilisateur{" + "ID_Utilisateur=" + ID_Utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", datenaissance=" + datenaissance + ", num_tel=" + num_tel + ", email=" + email + ", avatar=" + avatar + ", mdp=" + mdp + ", type_user=" + type_user + ", evaluation=" + evaluation + ", genre=" + genre + ", passwordRequestedAt=" + passwordRequestedAt + ", Token=" + Token + ", nbsignal=" + nbsignal + ", activated=" + activated + ", sponsor=" + sponsor + ", desactivated=" + desactivated + ", Liste_Collaborations=" + Liste_Collaborations + ", date_creation=" + date_creation + '}';
    }

 


    


    
}

