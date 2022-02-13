package collab.entities;

/**
 *
 * @author mouhi
 */
public class Salle_Collaboration {
    private int ID_Collab ;
    private Object chat ;
    private String URL_Collab ;
    private String Nom_Collab ;

    public int getID_Collab() {
        return ID_Collab;
    }

    public Object getChat() {
        return chat;
    }

    public String getURL_Collab() {
        return URL_Collab;
    }

    public String getNom_Collab() {
        return Nom_Collab;
    }

    public void setID_Collab(int ID_Collab) {
        this.ID_Collab = ID_Collab;
    }

    public void setChat(Object chat) {
        this.chat = chat;
    }

    public void setURL_Collab(String URL_Collab) {
        this.URL_Collab = URL_Collab;
    }

    public void setNom_Collab(String Nom_Collab) {
        this.Nom_Collab = Nom_Collab;
    }

    @Override
    public String toString() {
        return "Salle_Collaboration{" + "ID_Collab=" + ID_Collab + ", chat=" + chat + ", URL_Collab=" + URL_Collab + ", Nom_Collab=" + Nom_Collab + '}';
    }

    public Salle_Collaboration() {
    }
    
    public Salle_Collaboration(String URL_Collab, String Nom_Collab) {
        
        this.URL_Collab = URL_Collab;
        this.Nom_Collab = Nom_Collab;
    }
    
}
