package collab;
import collab.entities.Projet;
import collab.entities.Salle_Collaboration;
import collab.services.CollabService;
import collab.services.ProjetService;
import database.db;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import utilisateur.entities.utilisateur;
// @author mouhib
public class CollabTest {
    public static void main(String[] args) throws IOException{          
     ArrayList <utilisateur> list = new ArrayList();
        // test DB Connexion       
        db cnx = db.getInstance();    
        CollabService cs= new CollabService();
        ProjetService ps= new ProjetService();      
        // creation des scanner et buffers pour les inputs 
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        Scanner Input = new Scanner( System.in );
        // creation du collab        
        System.out.println("donnez un nom du collab");
        String name = reader.readLine();        
        System.out.println("donnez le nbre des collaborateur");   
        int nb = Input.nextInt();      
        for (int i = 0; i < nb; i++) {           
            System.out.println("donnez ID collaborateur");
            int id = Input.nextInt();
            utilisateur e = new utilisateur (id);
            list.add(e);
        }     
        System.out.println("Liste des collaborateur a ajouter");
        for ( int i=0 ; i<list.size();i++){
                utilisateur e = (utilisateur) list.get(i);
               System.out.println(e.toString());
            }   
        Salle_Collaboration salle = new Salle_Collaboration(list,"http://example.com/"+name,name,7);   
        cs.creer(salle,1);
        System.out.println(salle.toString());
         // test de l'affichage appartir du bd
        System.out.println(cs.afficher(7));       
        // test des modifications     
         System.out.println("rennomez le collab");
         String nname = reader.readLine();
         System.out.println("id du collab");
         int iid = Input.nextInt();
         cs.modifier(iid, nname);     
        // test du supression   
        System.out.println("id du collab a supprimer");
        int idS = Input.nextInt();
        cs.supprimer(idS);         
        // creaation du projet       
         System.out.println("donnez un nom du projet: ");
        String nameP = reader.readLine(); 
        System.out.println("decrire votre projet: ");   
        String desc = reader.readLine();        
        String Trello_Url = "https://trello.com/"+nameP ;             
        Projet projet = new Projet(7,nameP,desc,Trello_Url );   
        ps.creer(projet);
        System.out.println(projet.toString());        
       // test de l'affichage appartir du bd
         System.out.println(ps.afficher(7));        
        // test des modifications     
         System.out.println("rennomez le Projet");
         String nnameP = reader.readLine();
         System.out.println("id du Projet");
         int iidP = Input.nextInt();
         System.out.println("modifier la Description");
         String des = reader.readLine();
         ps.modifier(iidP, nnameP,des);        
        // test du supression
        System.out.println("id du projet a supprimer");
        int idp = Input.nextInt();
        ps.supprimer(idp);
    }
}
