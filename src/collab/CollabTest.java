package collab;
import collab.entities.Salle_Collaboration;
import collab.services.CollabService;
import database.db;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject; 

/**
 *
 * @author mouhi
 */
public class CollabTest {
    private Object Liste_Utilisateur ;
    public static void main(String[] args) {
        
        
    JSONArray jsonarray = new JSONArray(); 
    JSONObject jsonobject = new JSONObject();
        jsonobject.put("id",5); 
    JSONObject obj=new JSONObject();   
        obj.put("id",7);  
    JSONObject obj3=new JSONObject();  
        obj3.put("id",8);  
  
            jsonarray.add(jsonobject);
            jsonarray.add(obj3);
            jsonarray.add(obj);
            System.out.println(jsonarray.toString());
     
        // test DB Connexion
        db cnx = db.getInstance();    
        CollabService cs= new CollabService();   
        Salle_Collaboration salle = new Salle_Collaboration(jsonarray,"dd","wazzup");   
        cs.creer(salle);
        System.out.println(salle.toString());
    }
}
