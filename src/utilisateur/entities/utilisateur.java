/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur.entities;
import static utils.md5.getMd5;

/**
 *
 * @author malek
 */
public class utilisateur {
    private int ID_Utilisateur;
    private String nom;
    private String prenom;
    private int age;
    private int num_tel;
    private String email;
    private String mdp;
    private String type_user;
    private int evaluation;
    private String genre;
    private String interet;
    private String Liste_Collaborations;

    private int date_creation;

    public utilisateur(int ID_Utilisateur, String nom, String prenom, int age, int num_tel, String email, String mdp,
            String type_user, int evaluation, String genre) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.num_tel = num_tel;
        this.email = email;
        this.mdp = getMd5(mdp);
        this.type_user = type_user;
        this.evaluation = evaluation;
        this.genre = genre;
    }

    public utilisateur() {
    }

    public utilisateur(int ID_Utilisateur, String nom, String prenom) {
        this.ID_Utilisateur = ID_Utilisateur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public utilisateur(String nom, String prenom, int age, int num_tel, String email, String mdp, String type_user,
            int evaluation, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.num_tel = num_tel;
        this.email = email;
        this.mdp = getMd5(mdp);
        this.type_user = type_user;
        this.evaluation = evaluation;
        this.genre = genre;
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

    public int getAge() {
        return age;
    }

    public int getNum_tel() {
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setNum_tel(int num_tel) {
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

    public String getInteret() {
        return interet;
    }

    public void setInteret(String interet) {
        this.interet = interet;
    }

    @Override
    public String toString() {
        return "utilisateur{" + "ID_Utilisateur=" + ID_Utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", age="
                + age + ", num_tel=" + num_tel + ", email=" + email + ", mdp=" + mdp + ", type_user=" + type_user
                + ", evaluation=" + evaluation + ", genre=" + genre + ", interet=" + interet + ", Liste_Collaborations="
                + Liste_Collaborations + ", date_creation=" + date_creation + '}';
    }

}
